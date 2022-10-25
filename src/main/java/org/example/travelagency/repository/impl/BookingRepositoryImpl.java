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
        Session session = sessionFactory.getCurrentSession();
        return Optional.ofNullable(session.find(Booking.class, id));
    }

    @Override
    public Booking save(Booking booking) {
        Session session = sessionFactory.getCurrentSession();
        Integer id = (Integer) session.save(booking);
        return session.find(Booking.class, id);
    }

    @Override
    public Booking update(Booking booking) {
        Session session = sessionFactory.getCurrentSession();
        Booking toUpdate = session.find(Booking.class, booking.getId());
        toUpdate.setRoom(booking.getRoom());
        toUpdate.setCustomer(booking.getCustomer());
        toUpdate.setCheckIn(booking.getCheckIn());
        toUpdate.setCheckOut(booking.getCheckOut());
        return toUpdate;
    }

    @Override
    public void deleteById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Booking toDelete = session.find(Booking.class, id);
        session.delete(toDelete);
    }
}
