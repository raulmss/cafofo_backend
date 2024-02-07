package com.baseProject.cafofo.service.impl;

import com.baseProject.cafofo.dto.OfferDto;
import com.baseProject.cafofo.dto.OfferRequest;
import com.baseProject.cafofo.dto.OfferRequestDto;
import com.baseProject.cafofo.dto.OfferStatusRequest;
import com.baseProject.cafofo.entity.Customer;
import com.baseProject.cafofo.entity.Offer;
import com.baseProject.cafofo.entity.OfferStatus;
import com.baseProject.cafofo.entity.Property;
import com.baseProject.cafofo.exceptions.CustomerException;
import com.baseProject.cafofo.exceptions.OfferException;
import com.baseProject.cafofo.exceptions.PropertyException;
import com.baseProject.cafofo.helper.ListMapper;
import com.baseProject.cafofo.repo.CustomerRepo;
import com.baseProject.cafofo.repo.OfferRepo;
import com.baseProject.cafofo.repo.PropertyRepo;
import com.baseProject.cafofo.service.CustomerService;
import com.baseProject.cafofo.service.OfferService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    OfferRepo offerRepo;
    @Autowired
    ListMapper listMapper;
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    PropertyRepo propertyRepo;

    @Transactional
    public void save(Long customerId, OfferRequestDto offerRequest){
        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(()->new CustomerException("Customer not found with id"+customerId));
        Property property = propertyRepo.findById(offerRequest.getPropertyId())
                .orElseThrow(()->new PropertyException("Property not found with id" + offerRequest.getPropertyId()));
        Offer offer = new Offer();
        offer.setCustomer(customer);
        offer.setOfferPrice(offerRequest.getOfferPrice());
        offer.setOfferDate(LocalDateTime.now());
        offer.setOfferStatus(OfferStatus.PENDING);
        offer.setProperty(property);
        offerRepo.save(offer);
    }


}
