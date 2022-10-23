package org.example.travelagency.repository.impl;

import org.example.travelagency.model.Customer;
import org.example.travelagency.repository.CustomerRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    private final SessionFactory sessionFactory;

    public CustomerRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Customer> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select c from Customer c", Customer.class).getResultList();
    }

    public Optional<Customer> findByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query<Customer> query = session.createQuery("select c from Customer c where c.email = :e", Customer.class);
        query.setParameter("e", email);
        return query.uniqueResultOptional();
    }

    @Override
    public Optional<Customer> findById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return Optional.ofNullable(session.find(Customer.class, id));
    }

    @Override
    public Customer save(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        Integer id = (Integer) session.save(customer);
        return session.find(Customer.class, id);
    }

    @Override
    public Customer update(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        Customer toSave = session.find(Customer.class, customer.getId());
        toSave.setFirstName(customer.getFirstName());
        toSave.setLastName(customer.getLastName());
        toSave.setEmail(customer.getEmail());
        toSave.setPassword(customer.getPassword());
        toSave.setRole(customer.getRole());
        return toSave;
    }

    @Override
    public void deleteById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Customer customer = session.find(Customer.class, id);
        session.delete(customer);
    }
}
