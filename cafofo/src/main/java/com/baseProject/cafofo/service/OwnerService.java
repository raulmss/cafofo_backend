package com.baseProject.cafofo.service;

import com.baseProject.cafofo.dto.OfferDto;
import com.baseProject.cafofo.dto.OfferRequest;

import java.util.Collection;
import java.util.List;

public interface OwnerService {
    Collection<OfferDto> findOffersByPropertiesId(Long ownerId, Long propertiesId);

    void approveOffer(Long ownerId, Long propertiesId, Long offerId);


    void rejectOffer(Long ownerId, Long propertiesId, Long offerId);
}
