package com.app.service;

import com.app.entity.Customer;
import com.app.repository.CustomerRepository;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
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
    public Page<Customer> getCustomersWithPagination(int page, int limit, String field) {
        field = field == null ?"id":field;
        Page<Customer> allCustomers = customerRepository.findAll(PageRequest.of(page, limit, Sort.by(field)));
        return allCustomers;
    }

    //Sorting
    public List<Customer> getCustomersWithSorting(String field){
        List<Customer> allCustomers =  customerRepository.findAll(Sort.by(field));
        return allCustomers;
    }

    //Searching
//    public List<Customer> getCustomersWithSearching(String pattern){
//        List<Customer> allCustomers =  customerRepository.findAllByFirstNameOrLastNameOrEmailContains(pattern);
//        return allCustomers;
//    }


}