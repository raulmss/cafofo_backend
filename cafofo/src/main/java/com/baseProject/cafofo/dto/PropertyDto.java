package com.baseProject.cafofo.dto;

import com.baseProject.cafofo.entity.*;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

public class PropertyDto {
    private Long id;

    private String propertyName;

    private Collection<PropImage> image;


    private Collection<OfferDto> offers;


    private Owner owner;


    private Address address;


    private Double price;


    private Integer numberOfBed;

    private Integer numberOfBathRoom;

    private List<String> factAndFeatures;

    private HomeType homeType;

    private DealType dealType;

    private Double area;
}
