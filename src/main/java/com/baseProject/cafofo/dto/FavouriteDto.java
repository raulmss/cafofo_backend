package com.baseProject.cafofo.dto;

import com.baseProject.cafofo.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavouriteDto {

    private Long id;
    private Long propertyId;
    private String propertyName;
    private Collection<PropImage> image;

    private Address address;

    private Double price;

    private Integer numberOfBed;

    private Integer numberOfBathRoom;


//    private String factAndFeatures;
    private List<String> factAndFeatures;

    private HomeType homeType;

    private DealType dealType;

    private Double area;
}
