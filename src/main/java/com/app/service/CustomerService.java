package com.app.service;

import com.app.entity.Customer;
import com.app.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> saveCustomers(List<Customer> customers) {
        return customerRepository.saveAll(customers);
    }

    public Customer getCustomerById(int id) {
        return customerRepository.findById(id).orElse(null);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public String deleteCustomer(int id) {
        customerRepository.deleteById(id);
        return "Customer " + id + " removed!!";
    }

    public String deleteAllCustomers() {
        customerRepository.deleteAll();
        return "Delete all Customer!!";
    }

    public Customer updateCustomer(int id, Customer customer) {
        Customer existingCustomer = customerRepository.findById(id).orElse(null);
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setFirstName(customer.getFirstName());
        existingCustomer.setLastName(customer.getLastName());
        existingCustomer.setGender(customer.getGender());
        existingCustomer.setCreatedDate(LocalDateTime.now());
        return customerRepository.save(existingCustomer);
    }

    //Pagination
    public Page<Customer> getCustomersWithPagination(int page, int limit, String[] sort, String pattern) {

        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        if(sort[0].contains(",")){
            // will sort more than 2 fields
            // sortOrder="field, direction"
            // Let split sort
            for(String sortOrder : sort){
                String[] _sort = sortOrder.split(",");
                orders.add(getSortWithFieldAndDirection(_sort[1], _sort[0]));
            }
        } else {
            //sort =[field, direction]
            // here is the situation only have 1 sort param
            orders.add(getSortWithFieldAndDirection(sort[1],sort[0]));
        }

        Pageable pageable = PageRequest.of(page-1, limit, Sort.by(orders));
        Page<Customer> allCustomers = customerRepository.findByFirstNameContainingOrLastNameContaining(pattern, pattern, pageable);
        //Need caculate offset = limit*pageIndex -> If want in page 1 GUI, we need pageIndex =0 -> offset = 0*10=0;
        return allCustomers;
    }

    private Sort.Order getSortWithFieldAndDirection(String direction, String field) {
        Sort.Direction directionObj = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        return new Sort.Order(directionObj, field);
    }

    //Sorting
    public List<Customer> getCustomersWithSorting(String[] sort) {
        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        if(sort[0].contains(",")){
            // will sort more than 2 fields
            // sortOrder="field, direction"
            // Let split sort
            for(String sortOrder : sort){
                String[] _sort = sortOrder.split(",");
                orders.add(getSortWithFieldAndDirection(_sort[1], _sort[0]));
            }
        } else {
            //sort =[field, direction]
            // here is the situation only have 1 sort param
            orders.add(getSortWithFieldAndDirection(sort[1],sort[0]));
        }
        List<Customer> allCustomers = customerRepository.findAll(Sort.by(orders));
        return allCustomers;
    }

    //Searching
    public List<Customer> getCustomersWithSearching(String pattern) {
        List<Customer> allCustomers = customerRepository.findByFirstNameContainingOrLastNameContainingOrEmailContaining(pattern);
        return allCustomers;
    }


}