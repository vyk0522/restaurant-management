package com.onejava.service.impl;

import com.onejava.constant.TypeReferenceConstant;
import com.onejava.entity.Guest;
import com.onejava.exception.GuestNotFoundException;
import com.onejava.model.Filter;
import com.onejava.model.GuestDto;
import com.onejava.modelMapper.ModelMapper;
import com.onejava.repository.GuestRepository;
import com.onejava.repository.specification.GuestSpecification;
import com.onejava.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class GuestServiceImpl implements GuestService {
    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    GuestSpecification guestSpecification;

    @Override
    public List<GuestDto> readAllGuests() {
        List<Guest> guests = this.guestRepository.findAll();
        return ModelMapper
                .convert(guests, TypeReferenceConstant.LIST_OF_GUEST_DTO_TYPE_REFERENCE);
    }

    @Override
    public GuestDto readAGuest(Long id) {
        Optional<Guest> guest = this.guestRepository.findById(id);
        if (guest.isPresent()) {
            return ModelMapper.convert(guest.get(), GuestDto.class);
        }
        throw new GuestNotFoundException("Guest not found with id: " + id);
    }

    @Override
    public List<GuestDto> filterGuests(List<Filter> filters) {
        List<Guest> guests = this.guestSpecification.getFilterQueryResult(filters);
        return ModelMapper
                .convert(guests, TypeReferenceConstant.LIST_OF_GUEST_DTO_TYPE_REFERENCE);
    }

    @Override
    public ResponseEntity<GuestDto> createAGuest(GuestDto guestDto) {
        Guest guest = this.guestRepository.save(ModelMapper.convert(guestDto, Guest.class));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(guest.getId())
                .toUri();
        return ResponseEntity
                .created(location)
                .body(ModelMapper.convert(guest, GuestDto.class));
    }
}
