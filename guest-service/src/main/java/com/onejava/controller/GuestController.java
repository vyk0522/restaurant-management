package com.onejava.controller;

import com.onejava.model.GuestDto;
import com.onejava.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/*
http://localhost:8091/api/v1/guests
 */
@RestController
@RequestMapping("/api/v1/guests")
public class GuestController {
    @Autowired
    public GuestService guestService;

    @GetMapping
    public List<GuestDto> getAllGuests() {
        return guestService.readAllGuests();
    }

    @GetMapping("/{id}")
    public GuestDto getAGuest(@PathVariable Long id) {
        return guestService.readAGuest(id);
    }

}