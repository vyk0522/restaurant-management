package com.onejava.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link com.onejava.entity.Guest} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestDto implements Serializable{
    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String address;
    private String country;
    private String state;
    private String phoneNumber;
}
