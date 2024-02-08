package com.baseProject.cafofo.dto;

import com.baseProject.cafofo.entity.DealType;
import com.baseProject.cafofo.entity.HomeType;
import com.baseProject.cafofo.entity.PropertyStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferPropertyDto {

    private String propertyName;
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
    private Collection<ImageDto> image= new ArrayList<>();

}
