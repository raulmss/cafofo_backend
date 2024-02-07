package com.baseProject.cafofo.service.impl;


import com.baseProject.cafofo.dto.OfferDto;
import com.baseProject.cafofo.dto.OfferRequest;
import com.baseProject.cafofo.entity.*;
import com.baseProject.cafofo.exceptions.CustomerException;
import com.baseProject.cafofo.exceptions.OfferException;
import com.baseProject.cafofo.exceptions.OwnerException;
import com.baseProject.cafofo.exceptions.PropertyException;
import com.baseProject.cafofo.repo.CustomerRepo;
import com.baseProject.cafofo.repo.OfferRepo;
import com.baseProject.cafofo.repo.PropertyRepo;
import com.baseProject.cafofo.service.CustomerService;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

import com.baseProject.cafofo.dto.OwnerPropertyCriteriaRequest;
import com.baseProject.cafofo.dto.PropertyDto;
import com.baseProject.cafofo.entity.Property;
import com.baseProject.cafofo.helper.ListMapper;
import com.baseProject.cafofo.repo.OwnerPropertySearchDao;
import com.baseProject.cafofo.repo.OwnerRepo;
import com.baseProject.cafofo.service.OwnerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    OwnerRepo ownerRepo;
    @Autowired
    ListMapper listMapper;
    @Autowired
    PropertyRepo propertyRepo;
    @Autowired
    OfferRepo offerRepo;
    @Autowired
    OwnerPropertySearchDao ownerPropertySearchDao;

    @Transactional
    public Collection<PropertyDto> getOwnerPropertiesByPlaced(Long ownerId){
        Collection<PropertyDto> propertyDtoList = listMapper.mapList( ownerRepo.getOwnerPropertiesByPlaced(ownerId), new PropertyDto()) ;
        return propertyDtoList;
    }
    @Transactional
    @Override
    public List<Property> searchOwnerMinMaxProperty(Long ownerId, String dealType, Double minPrice,
                                                    Double maxPrice, Double numBed, Double numBath,
                                                    String homeType, Double minArea, Double maxArea,
                                                    String factAndFactory) {
        OwnerPropertyCriteriaRequest ownerPropertyCriteriaRequest = new OwnerPropertyCriteriaRequest();

        ownerPropertyCriteriaRequest.setDealType (dealType);
        ownerPropertyCriteriaRequest.setMinPrice(minPrice);
        ownerPropertyCriteriaRequest.setMaxPrice(maxPrice);
        ownerPropertyCriteriaRequest.setHomeType(homeType);
        ownerPropertyCriteriaRequest.setNumBath(numBath);
        ownerPropertyCriteriaRequest.setNumBed(numBed);
        ownerPropertyCriteriaRequest.setFactAndFactory(factAndFactory);
        ownerPropertyCriteriaRequest.setMinArea(minArea);
        ownerPropertyCriteriaRequest.setMaxArea(maxArea);
        ownerPropertyCriteriaRequest.setOwnerId(ownerId);
        return ownerPropertySearchDao.findAllByCriteria(ownerPropertyCriteriaRequest) ;
    }
  
      public Collection<OfferDto> findOffersByPropertiesId(Long ownerId, Long propertiesId){
        return listMapper.mapList(ownerRepo.findOffersByPropertiesId(ownerId,propertiesId), new OfferDto());
    }
  
      public void approveOffer(Long ownerId, Long propertiesId, Long offerId){
        Owner owner = ownerRepo.findById(ownerId)
                .orElseThrow(()->new OwnerException("Owner not found with id: " + ownerId));
        Property property= propertyRepo.findById(propertiesId)
                .orElseThrow(()->new PropertyException("Property not found with id: "+ propertiesId));
        Offer offer = offerRepo.findById(offerId)
                .orElseThrow(()->new OfferException("Offer not found with id: "+offerId));
        offer.setOfferStatus(OfferStatus.ACCEPTED);
        offerRepo.save(offer);
    }

    public void rejectOffer(Long ownerId, Long propertiesId, Long offerId){
        Owner owner = ownerRepo.findById(ownerId)
                .orElseThrow(()->new OwnerException("Owner not found with id: " + ownerId));
        Property property= propertyRepo.findById(propertiesId)
                .orElseThrow(()->new PropertyException("Property not found with id: "+ propertiesId));
        Offer offer = offerRepo.findById(offerId)
                .orElseThrow(()->new OfferException("Offer not found with id: "+offerId));
        offer.setOfferStatus(OfferStatus.REJECTED);
        offerRepo.save(offer);

    }

}
