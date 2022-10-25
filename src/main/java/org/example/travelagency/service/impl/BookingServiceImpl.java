package org.example.travelagency.service.impl;

import org.example.travelagency.exception.BookingNotFoundException;
import org.example.travelagency.model.Booking;
import org.example.travelagency.repository.BookingRepository;
import org.example.travelagency.service.BookingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking create(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Booking readById(Integer id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException(String.format("Booking with id %d does not exist", id)));
    }

    @Override
    public Booking update(Booking booking) {
        return bookingRepository.update(booking);
    }

    @Override
    public void delete(Integer id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }
}
