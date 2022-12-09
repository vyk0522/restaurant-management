package com.onejava.service;

import com.onejava.entity.Guest;
import com.onejava.model.Filter;
import com.onejava.model.GuestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface GuestService {
    List<GuestDto> readAllGuests();

    GuestDto readAGuest(Long id);

    List<GuestDto> filterGuests(List<Filter> filters);

    ResponseEntity<GuestDto> createAGuest(GuestDto guestDto);

    GuestDto updateAGuest(@PathVariable Long id, @RequestBody GuestDto guestDto);

    void deleteAGuest(Long id);

}
