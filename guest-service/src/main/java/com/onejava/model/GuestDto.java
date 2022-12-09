package com.onejava.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String firstName;
    private String lastName;
    private String emailAddress;

    @JsonProperty("postalAddress") // postalAddress during serialization(json string)
    @JsonAlias("address")  // address during deserialization(class)
    private String location;

    private String country;
    private String state;
    private String phoneNumber;
}
