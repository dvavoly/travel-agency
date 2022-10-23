package org.example.travelagency.repository.impl;

import org.example.travelagency.model.Booking;
import org.example.travelagency.repository.BookingRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookingRepositoryImpl implements BookingRepository {
    private final SessionFactory sessionFactory;

    public BookingRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Booking> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select b from Booking b", Booking.class).getResultList();
    }

    @Override
    public Optional<Booking> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Booking save(Booking booking) {
        return null;
    }

    @Override
    public Booking update(Booking booking) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
