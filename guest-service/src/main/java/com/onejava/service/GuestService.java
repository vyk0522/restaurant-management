package com.onejava.service;

import com.onejava.model.GuestDto;

import java.util.List;

public interface GuestService {
    List<GuestDto> readAllGuests();

    GuestDto readAGuest(Long id);
}