package com.baseProject.cafofo.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class AddressDto {

    private Long id;

    private String country;

    private String state;

    private String city;

    private String street;

    private String number;

    private String zip;
}
