package org.example.travelagency.service.impl;

import org.example.travelagency.exception.CustomerNotFoundException;
import org.example.travelagency.model.Customer;
import org.example.travelagency.repository.CustomerRepository;
import org.example.travelagency.service.CustomerService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerServiceImpl(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Customer create(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepository.save(customer);
    }

    @Override
    public Customer readById(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("Customer with %s not found", id)));
    }

    @Override
    public Customer update(Customer customer) {
        return customerRepository.update(customer);
    }

    @Override
    public void delete(Integer id) {
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }
}
