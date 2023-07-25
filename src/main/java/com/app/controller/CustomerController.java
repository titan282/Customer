package com.app.controller;

import com.app.entity.Customer;
import com.app.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public String gretting() {
        return "Hello";
    }

    //CRUD
//    @GetMapping("/customers/all")
//    public List<Customer> findAllCustomers(){
//        List<Customer> allCustomers = customerService.getAllCustomers();
//        return  allCustomers;
//    }
    //SortAll
    @GetMapping("/customers/all")
    public List<Customer> getCustomers(@RequestParam(defaultValue = "id,asc") String[] sort) {
        return customerService.getCustomersWithSorting(sort);
    }

    @GetMapping("/customers/{id}")
    public Customer findCustomerById(@PathVariable int id) {
        return customerService.getCustomerById(id);
    }

    //Pagination and Sorting
    @GetMapping("/customers")
    public Page<Customer> getCustomersWithPagination(@RequestParam(defaultValue = "1" ) int page,
                                                     @RequestParam(defaultValue = "5") int limit,
                                                     @RequestParam(defaultValue = "id,asc") String[] sort,
                                                     @RequestParam(defaultValue = "") String pattern){
        return customerService.getCustomersWithPagination(page, limit,sort,pattern);
    }



    //Searching
    @GetMapping("/customers/searching")
    public List<Customer> getCustomersWithSearching(@RequestParam String pattern) {
        return customerService.getCustomersWithSearching(pattern);
    }

    @PostMapping("/customers")
    public List<Customer> addCustomer( @RequestBody List<Customer> customers) {
        return customerService.saveCustomers(customers);
    }
    @PostMapping("/customer")
    public Customer addCustomer(@RequestBody Customer customer) {
//        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(customer.getEmail());
//        if(matcher.matches()){
//           customerService.saveCustomer(customer);
//           return "The customer has been added";
//        }else {
//            return "Invalid email, Please input again";
//        }
        return customerService.saveCustomer(customer);
    }

    @PutMapping("/customers/{id}")
    public Customer updateCustomer(@RequestBody Customer newUpdateCustomer, @PathVariable int id) {
        return customerService.updateCustomer(id, newUpdateCustomer);
    }

    @DeleteMapping("/customers/{id}")
    public String deleteCustomer(@PathVariable int id) {
        return customerService.deleteCustomer(id);
    }

    @DeleteMapping("/customers")
    public String deleteCustomer() {
        return customerService.deleteAllCustomers();
    }


}
