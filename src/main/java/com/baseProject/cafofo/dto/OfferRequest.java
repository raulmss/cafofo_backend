package com.baseProject.cafofo.dto;

import com.baseProject.cafofo.entity.Customer;
import com.baseProject.cafofo.entity.OfferStatus;
import com.baseProject.cafofo.entity.Property;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferRequest {
    private Long customerId;
    private LocalDateTime offerDate;
    private Long propertyId;
    private Double offerPrice;
}
