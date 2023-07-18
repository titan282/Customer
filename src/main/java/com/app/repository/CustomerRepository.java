package com.app.repository;

import com.app.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
//    Page<Customer> findByFirstNameOrLastNameContaining(String pattern);
    List<Customer> findByFirstNameContainingOrLastNameContaining(String pattern1, String pattern2);

    @Query("SELECT c FROM Customer c WHERE c.firstName LIKE %?1%  OR c.lastName LIKE %?1%  OR c.email LIKE %?1% ")
    List<Customer> findByFirstNameContainingOrLastNameContainingOrEmailContaining(String pattern);
}
