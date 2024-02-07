package com.baseProject.cafofo.service;

import com.baseProject.cafofo.DTO.FavouriteDto;
import com.baseProject.cafofo.entity.Offer;

import java.util.List;

public interface CustomerService {

    String addToFavorites(Long userId, Long propertyId);

    String removeFromFavorites(Long userId, Long propertyId);

    List<FavouriteDto> getFavorites(Long userId);

    List<Offer> getOffersByUser(Long userId);

    String cancelOffer(Long offerId, Long userId);

    String maintainOfferByPrice(Long offerId,double price, Long userId);
}
