package com.baseProject.cafofo.dto;

import com.baseProject.cafofo.entity.OfferStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferStatusRequest {
    private OfferStatus offerStatus;
}
