package com.baseProject.cafofo.dto;

import com.baseProject.cafofo.entity.Offer;
import com.baseProject.cafofo.entity.Property;
import com.baseProject.cafofo.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private UserDto user;
    private Collection<OfferDto> offers;
    private Collection<Property> favoriteProperties;
}
