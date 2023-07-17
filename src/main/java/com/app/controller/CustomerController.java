package com.app.controller;

import com.app.entity.Customer;
import com.app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @GetMapping("/customers")
    public List<Customer> findAllCustomers(){
        List<Customer> allCustomers = customerService.getAllCustomers();
        return  allCustomers;
    }

    @GetMapping("/customers/{id}")
    public Customer findCustomerById(@PathVariable int id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping("/customers")
    public List<Customer> addCustomer(@RequestBody List<Customer> customers) {
        return customerService.saveCustomers(customers);
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

    //Pagination
    @GetMapping("/customers/page")
    public Page<Customer> getCustomersWithPagination(@RequestParam int page, @RequestParam int limit,@RequestParam(name = "sortBy", required = false) String field){
        return customerService.getCustomersWithPagination(page, limit,field);
    }

    //Sorting
    @GetMapping("/customers/sorting")
    public List<Customer> getCustomersWithSorting(@RequestParam String field) {
        return customerService.getCustomersWithSorting(field);
    }
}
