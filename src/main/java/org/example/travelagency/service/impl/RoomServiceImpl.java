package org.example.travelagency.service.impl;

import org.example.travelagency.model.Room;
import org.example.travelagency.repository.RoomRepository;
import org.example.travelagency.service.RoomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room create(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room readById(Integer id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Room with id %s does not exist", id)));
    }

    @Override
    public Room update(Room room) {
        return roomRepository.update(room);
    }

    @Override
    public void delete(Integer id) {
        roomRepository.deleteById(id);
    }

    @Override
    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    @Override
    public List<Room> getAllByHotelId(Integer id) {
        return roomRepository.findAll().stream()
                .filter(r -> r.getHotel().getId().equals(id))
                .collect(Collectors.toList());
    }
}
