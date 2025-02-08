package com.baseProject.cafofo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyCriteriaRequest {
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
