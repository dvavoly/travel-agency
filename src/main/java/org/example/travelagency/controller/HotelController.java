package org.example.travelagency.controller;

import org.example.travelagency.model.Hotel;
import org.example.travelagency.service.HotelService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hotels")
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("hotels", hotelService.getAll());
        return "hotel/hotels";
    }

    @GetMapping("/{id}")
    public String getHotelById(@PathVariable Integer id, Model model) {
        model.addAttribute("hotel", hotelService.readById(id));
        return "hotel/hotel";
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("hotel", new Hotel());
        return "hotel/create-hotel";
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/create")
    public String create(@Validated @ModelAttribute("hotel") Hotel hotel, BindingResult result) {
        if (result.hasErrors()) {
            return "hotel/create-hotel";
        }
        hotelService.create(hotel);
        return "redirect:/hotels";
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/{id}/update")
    public String update(@PathVariable Integer id, Model model) {
        model.addAttribute("hotel", hotelService.readById(id));
        return "hotel/update-hotel";
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/{id}/update")
    public String update(@PathVariable Integer id, Model model, @Validated @ModelAttribute("hotel") Hotel hotel, BindingResult result) {
        if (result.hasErrors()) {
            return "hotel/update-hotel";
        }
        hotelService.update(hotel);
        return "redirect:/hotels";
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/{id}/delete")
    public String deleteHotelById(@PathVariable Integer id, Model model) {
        hotelService.delete(id);
        model.addAttribute("hotels", hotelService.getAll());
        return "hotel/hotels";
    }
}
