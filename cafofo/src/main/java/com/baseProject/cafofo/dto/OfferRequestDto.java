package com.baseProject.cafofo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferRequestDto {

    private LocalDateTime offerDate;
    private Long propertyId;
    private Double offerPrice;
}
