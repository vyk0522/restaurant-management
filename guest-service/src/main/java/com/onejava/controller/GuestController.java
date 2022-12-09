package com.onejava.controller;

import com.onejava.model.Filter;
import com.onejava.model.GuestDto;
import com.onejava.service.GuestService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
http://localhost:8091/api/v1/guests
 */
@RestController
@RequestMapping("/api/v1/guests")
@Validated // Required for PathVariable validation
public class GuestController {
    @Autowired
    public GuestService guestService;

    @GetMapping
    public List<GuestDto> getAllGuests() {
        return guestService.readAllGuests();
    }

    @GetMapping("/{id}")
    public GuestDto getAGuest(@PathVariable @Min(1) Long id) {
        return guestService.readAGuest(id);
    }

    @PostMapping("/filter")
    public List<GuestDto> filterGuests(@RequestBody List<Filter> filters) {
        return guestService.filterGuests(filters);
    }

    @PostMapping
    public ResponseEntity<GuestDto> addAGuest(@Valid @RequestBody GuestDto guestDto){
        return guestService.createAGuest(guestDto);
    }

    @PutMapping("/{id}")
    public GuestDto updateAGuest(@PathVariable Long id, @Valid @RequestBody GuestDto guestDto){
        return guestService.updateAGuest(id, guestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT) // 205 Code
    public void deleteAGuest(@PathVariable Long id){
        this.guestService.deleteAGuest(id);
    }

}