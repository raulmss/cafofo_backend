package com.baseProject.cafofo.service;

import com.baseProject.cafofo.dto.OfferRequestDto;


public interface CustomerService {

    void save(Long customerId, OfferRequestDto offerRequest);

}
