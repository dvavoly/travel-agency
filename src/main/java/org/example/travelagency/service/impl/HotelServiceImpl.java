package org.example.travelagency.service.impl;

import org.example.travelagency.exception.HotelNotFoundException;
import org.example.travelagency.model.Hotel;
import org.example.travelagency.repository.HotelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HotelServiceImpl implements org.example.travelagency.service.HotelService {

    private final HotelRepository hotelRepository;

    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public Hotel create(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel readById(Integer id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException(String.format("Hotel with %d does not exist", id)));
    }

    @Override
    public Hotel update(Hotel hotel) {
        return hotelRepository.update(hotel);
    }

    @Override
    public void delete(Integer id) {
        hotelRepository.deleteById(id);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }
}
