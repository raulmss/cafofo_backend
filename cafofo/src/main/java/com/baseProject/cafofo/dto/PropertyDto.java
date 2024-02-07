package com.baseProject.cafofo.dto;

import com.baseProject.cafofo.entity.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyDto {

    private Long id;
    private String propertyName;
    private Collection<ImageDto> image;
    private Collection<OfferDto> offers;
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

}
