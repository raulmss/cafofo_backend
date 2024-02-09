package com.baseProject.cafofo.service;

import com.baseProject.cafofo.dto.FavouriteDto;
import com.baseProject.cafofo.dto.OfferListDto;
import com.baseProject.cafofo.entity.Offer;
import com.baseProject.cafofo.dto.OfferRequestDto;

import java.util.List;

public interface CustomerService {
  
    Long save(Long customerId, OfferRequestDto offerRequest);

    String addToFavorites(Long userId, Long propertyId);

    String removeFromFavorites(Long userId, Long propertyId);

    List<FavouriteDto> getFavorites(Long userId);

    List<OfferListDto> getOffersByUser(Long userId);

    String cancelOffer(Long offerId, Long userId);

    String maintainOfferByPrice(Long offerId,double price, Long userId);
}
