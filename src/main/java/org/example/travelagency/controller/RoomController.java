package org.example.travelagency.controller;

import org.example.travelagency.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/{id}")
    public String getAllByHotelId(@PathVariable Integer id, Model model) {
        model.addAttribute("rooms", roomService.getAllByHotelId(id));
        return "room/rooms";
    }
}
