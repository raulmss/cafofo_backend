package com.baseProject.cafofo.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private String country;
    private String state;
    private String city;
    private String street;
    private String number;
    private String zip;
}
