package com.baseProject.cafofo.service.impl;


import com.baseProject.cafofo.dto.OfferDto;
import com.baseProject.cafofo.dto.OfferRequest;
import com.baseProject.cafofo.entity.*;
import com.baseProject.cafofo.exceptions.*;
import com.baseProject.cafofo.repo.CustomerRepo;
import com.baseProject.cafofo.repo.OfferRepo;
import com.baseProject.cafofo.repo.PropertyRepo;
import com.baseProject.cafofo.service.CustomerService;
import com.baseProject.cafofo.service.EmailService;
import jakarta.mail.MessagingException;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.NotFound;
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


import java.util.ArrayList;
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

    @Autowired
    EmailService emailService;

    CustomerRepo customerRepo;

    @Transactional
    public Collection<PropertyDto> getOwnerPropertiesByPlaced(Long ownerId){
        Collection<Property> properties = ownerRepo.getOwnerPropertiesByPlaced(ownerId);
        if(properties==null || properties.equals(null)){
            throw new CafofoApplicationException("There is no properties for this owner");
        }

        Collection<PropertyDto> propertyDtoList = listMapper.mapList( properties, new PropertyDto()) ;
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
        List<Property> properties = new ArrayList<>();

        for(Property p : ownerPropertySearchDao.findAllByCriteria(ownerPropertyCriteriaRequest)){
            if(p.getOwner().getId() == ownerId){
                properties.add(p);
            }
        }
         return properties;

    }
  
      public Collection<OfferDto> findOffersByPropertiesId(Long ownerId, Long propertiesId){
          Collection<Offer> offers = ownerRepo.findOffersByPropertiesId(ownerId,propertiesId);
          if(offers==null || offers.equals(null)){
              throw new CafofoApplicationException("There is no properties for this owner");
          }
        return listMapper.mapList(offers, new OfferDto());
    }

      @Override
      @Transactional
      public void approveOffer(Long ownerId, Long propertiesId, Long offerId){
        Offer offer = offerRepo.findById(propertiesId,offerId)
                .orElseThrow(()->new OfferException("Offer not found with id: "+offerId+ " property id "+propertiesId));
        offer.setOfferStatus(OfferStatus.ACCEPTED);
        offerRepo.save(offer);
        emailToCustomer(offerId,offer.getCustomer().getId(),OfferStatus.ACCEPTED);
    }

    @Transactional
    @Override
    public void rejectOffer(Long ownerId, Long propertiesId, Long offerId){
        Offer offer = offerRepo.findById(offerId)
                .orElseThrow(()->new OfferException("Offer not found with id: "+offerId));
        offer.setOfferStatus(OfferStatus.REJECTED);
        offerRepo.save(offer);

        emailToCustomer(offerId,offer.getCustomer().getId(),OfferStatus.REJECTED);
    }

    private void emailToCustomer(Long offerId, Long customerId, OfferStatus offerStatus){

        String customerEmail=ownerRepo.getCustomerEmail(customerId);
        Offer offer = ownerRepo.checkOffer(customerId, offerId);

        System.out.println("customer email : "+customerEmail+" offerId: "+offerId);

        // Send email to customer
        String subject = "";
        String body="";
        if(offerStatus.equals(OfferStatus.ACCEPTED)){
            subject = "Cafofo Offer Acceptance";
            body = "Dear Customer, your offer (Offer Price: "+offer.getOfferPrice()+" Property ID: "+offer.getProperty().getId() +" Name; "+offer.getProperty().getPropertyName() +") has been accepted.";
        }
        if(offerStatus.equals(OfferStatus.REJECTED)){
            subject = "Cafofo Offer Reject";
            body = "Dear Customer, your offer (Offer Price: "+offer.getOfferPrice()+" Property ID: "+offer.getProperty().getId() +" Name; "+offer.getProperty().getPropertyName() +") has been rejected.";

        }
        System.out.println("email to customer");
      //  emailService.sendEmail(customerEmail, subject, body);

    }
    @Override
    public Owner findById(Long ownerId){
        return ownerRepo.findById(ownerId)
                .orElseThrow(()->new CafofoApplicationException("There is no owner"));
    }

    @Override
    public List<Offer> findOfferByOwnerId(Long ownerId) {
        return ownerRepo.findOfferByOwnerId(ownerId);
    }


}
