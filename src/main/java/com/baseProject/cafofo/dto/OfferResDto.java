package com.baseProject.cafofo.dto;

import com.baseProject.cafofo.entity.OfferStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferResDto {

    private Long id;
    //    private CustomerDto customer;
    private OfferStatus offerStatus;
    private LocalDateTime offerDate;
    private Double offerPrice;
   // private OfferPropertyDto property;
    private boolean cancel_flag;
}
