package com.baseProject.cafofo.dto;

import com.baseProject.cafofo.entity.Property;

import java.util.Collection;

public class OwnerDto {
    private Long id;

    private UserDto User;

    private Collection<Property> properties;
}
