package org.example.travelagency.controller;

import org.example.travelagency.model.Booking;
import org.example.travelagency.model.Customer;
import org.example.travelagency.model.Room;
import org.example.travelagency.service.BookingService;
import org.example.travelagency.service.CustomerService;
import org.example.travelagency.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;
    private final BookingService bookingService;
    private final CustomerService customerService;

    public RoomController(RoomService roomService, BookingService bookingService, CustomerService customerService) {
        this.roomService = roomService;
        this.bookingService = bookingService;
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public String getAllByHotelId(@PathVariable Integer id, Model model) {
        model.addAttribute("rooms", roomService.getAllByHotelId(id));
        return "room/rooms";
    }

    @GetMapping("/{id}/booking")
    public String bookRoom(@PathVariable("id") Integer roomId, Model model) {
        model.addAttribute("room", roomService.readById(roomId));
        model.addAttribute("booking", new Booking());
        return "room/room";
    }

    @PostMapping("/{id}/booking")
    public String bookRoom(@PathVariable("id") Integer roomId,
                           @Validated @ModelAttribute("booking") Booking booking,
                           BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "room/room";
        }
        Customer customer = customerService.readById(booking.getCustomer().getId());
        Room room = roomService.readById(roomId);
        List<Booking> allBookingsCurrentRoom = room.getBookings();
        booking.setRoom(room);
        booking.setCustomer(customer);
        if (allBookingsCurrentRoom.stream().noneMatch(b -> hasAnyBookingInRange(b, booking))) {
            bookingService.create(booking);
        } else {
            model.addAttribute("room", roomService.readById(roomId));
            model.addAttribute("error", "This date is busy");
            return "room/room";
        }
        return String.format("redirect:/bookings/%s", booking.getCustomer().getId());
    }

    private static boolean hasAnyBookingInRange(Booking existed, Booking current) {
        return !((existed.getCheckIn().isAfter(current.getCheckIn()) && existed.getCheckIn().isAfter(current.getCheckOut()))
                || (existed.getCheckOut().isBefore(current.getCheckIn()) && existed.getCheckOut().isBefore(current.getCheckOut())));
    }
}
