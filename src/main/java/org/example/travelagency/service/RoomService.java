package org.example.travelagency.service;

import org.example.travelagency.model.Room;

import java.util.List;

public interface RoomService extends GenericService<Room, Integer> {
    List<Room> getAllByHotelId(Integer id);
}
