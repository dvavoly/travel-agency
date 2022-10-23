package org.example.travelagency.controller;

import org.example.travelagency.service.HotelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final HotelService hotelService;

    public HomeController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("hotels", hotelService.getAll());
        return "home";
    }
}
