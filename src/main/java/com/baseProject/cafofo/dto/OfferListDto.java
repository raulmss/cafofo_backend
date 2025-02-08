package com.baseProject.cafofo.dto;

import com.baseProject.cafofo.entity.Customer;
import com.baseProject.cafofo.entity.OfferStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferListDto {

    private Long id;

    private Customer customer;

    private OfferStatus offerStatus;

    private double offerPrice;

    private LocalDateTime offerDate;

    private PropertyDto propertyDto;

    private boolean cancel_flag;

    private boolean isFavorited;
}
