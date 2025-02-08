package com.baseProject.cafofo.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OwnerPropertyCriteriaRequest {
    Long ownerId;
    String dealType;
    Double minPrice;
    Double maxPrice;
    Double numBed;
    Double numBath;
    String homeType;
    Double minArea;
    Double maxArea;
    String factAndFactory;
}
