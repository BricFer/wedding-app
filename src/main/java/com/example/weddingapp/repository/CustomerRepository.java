package com.example.weddingapp.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.weddingapp.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Optional<Customer> findByUsernameAndPassword(String username, String password);
    Optional<Customer> findByUsername(String username);
    Optional<Customer> findByEmail(String email);
}
