package org.example.travelagency.repository.impl;

import org.example.travelagency.model.Hotel;
import org.example.travelagency.repository.HotelRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class HotelRepositoryImpl implements HotelRepository {
    private final SessionFactory sessionFactory;

    public HotelRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Hotel> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select h from Hotel h", Hotel.class).getResultList();
    }

    @Override
    public Optional<Hotel> findById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return Optional.ofNullable(session.find(Hotel.class, id));
    }

    @Override
    public Hotel save(Hotel hotel) {
        Session session = sessionFactory.getCurrentSession();
        Integer id = (Integer) session.save(hotel);
        return session.find(Hotel.class, id);
    }

    @Override
    public Hotel update(Hotel hotel) {
        Session session = sessionFactory.getCurrentSession();
        Hotel toUpdate = session.find(Hotel.class, hotel.getId());
        toUpdate.setName(hotel.getName());
        toUpdate.setAddress(hotel.getAddress());
        toUpdate.setCountry(hotel.getCountry());
        return toUpdate;
    }

    @Override
    public void deleteById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Hotel hotel = session.find(Hotel.class, id);
        session.delete(hotel);
    }
}
