package com.onejava.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @JsonIgnore
    private Long id;
    @NotNull(message = "FirstName is missing")
    private String firstName;
    private String lastName;
    @Email
    private String emailAddress;

    //@JsonProperty("postalAddress") // postalAddress during serialization(json string)
    //@JsonAlias("address")  // address during deserialization(class)
    @NotNull(message = "Address is missing")
    @Size(min = 5, message = "Address should be at least 5 characters")
    private String address;

    private String country;
    private String state;
    @NotNull(message = "PhoneNumber is missing")
    private String phoneNumber;
}
