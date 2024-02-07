package com.baseProject.cafofo.dto;

import com.baseProject.cafofo.entity.Customer;
import com.baseProject.cafofo.entity.OfferStatus;
import com.baseProject.cafofo.entity.Property;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferDto {

    private Long id;
    private Customer customer;
    private OfferStatus offerStatus;
    private LocalDateTime offerDate;
    private Double offerPrice;
    private Property property;

}
