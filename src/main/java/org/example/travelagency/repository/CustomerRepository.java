package org.example.travelagency.repository;

import org.example.travelagency.model.Customer;

import java.util.Optional;

public interface CustomerRepository extends GenericRepository<Customer, Integer> {
    Optional<Customer> findByEmail(String email);
}
