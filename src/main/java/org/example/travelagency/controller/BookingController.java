package org.example.travelagency.controller;

import org.example.travelagency.model.Booking;
import org.example.travelagency.service.BookingService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PreAuthorize("hasRole('MANAGER') or authentication.principal.id == #id")
    @GetMapping("/bookings/{id}")
    public String getBookingByUserId(@PathVariable Integer id, Model model) {
        List<Booking> customerBookings = bookingService.getAll().stream()
                .filter(b -> b.getCustomer().getId().equals(id))
                .collect(Collectors.toList());
        model.addAttribute("bookings", customerBookings);
        return "booking/booking";
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/bookings")
    public String getAll(Model model) {
        model.addAttribute("bookings", bookingService.getAll());
        return "booking/bookings";
    }
}
