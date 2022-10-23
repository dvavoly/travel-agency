package org.example.travelagency.repository.impl;

import org.example.travelagency.model.Room;
import org.example.travelagency.repository.RoomRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoomRepositoryImpl implements RoomRepository {
    private final SessionFactory sessionFactory;

    public RoomRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Room> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select r from Room r", Room.class).getResultList();
    }

    @Override
    public Optional<Room> findById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return Optional.ofNullable(session.find(Room.class, id));
    }

    @Override
    public Room save(Room room) {
        Session session = sessionFactory.getCurrentSession();
        Integer id = (Integer) session.save(room);
        return session.find(Room.class, id);
    }

    @Override
    public Room update(Room room) {
        Session session = sessionFactory.getCurrentSession();
        Room toUpdate = session.find(Room.class, room.getId());
        toUpdate.setHotel(room.getHotel());
        toUpdate.setRoomCost(room.getRoomCost());
        toUpdate.setRoomNumber(room.getRoomNumber());
        toUpdate.setRoomType(room.getRoomType());
        return toUpdate;
    }

    @Override
    public void deleteById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Room room = session.find(Room.class, id);
        session.delete(room);
    }
}
