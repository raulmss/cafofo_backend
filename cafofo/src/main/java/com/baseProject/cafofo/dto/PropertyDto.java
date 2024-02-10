package com.baseProject.cafofo.dto;

import com.baseProject.cafofo.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyDto {

    private Long id;
    private String propertyName;
    private Collection<ImageDto> image = new ArrayList<>();
    private Collection<OfferResDto> offers= new ArrayList<>();
    private OwnerDto owner;
    private AddressDto address;
    private Double price;
    private Integer numberOfBed;
    private Integer numberOfBathRoom;
    private String factAndFeatures;
    private HomeType homeType;
    private DealType dealType;
    private Double area;
    private Boolean approvalStatus;
    private PropertyStatus propertyStatus;
    private Boolean isFavorite;



}
