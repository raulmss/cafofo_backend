package com.baseProject.cafofo.service;

import com.baseProject.cafofo.dto.OfferDto;
import com.baseProject.cafofo.dto.OfferRequest;
import com.baseProject.cafofo.dto.OfferStatusRequest;
import com.baseProject.cafofo.entity.OfferStatus;

import java.util.Collection;

public interface OfferService {
    Collection<OfferDto> findAll();

    OfferDto findById(long offerId);

  //  void save(OfferRequest offerRequest);

    void update(long offerId, OfferStatusRequest offerStatus);

    void delete(long offerId);

    Collection<OfferDto> findAllById(Long userId);
}
