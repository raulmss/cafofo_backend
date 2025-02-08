package com.baseProject.cafofo.service.impl;

import com.baseProject.cafofo.dto.OfferDto;
import com.baseProject.cafofo.dto.OfferRequest;
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
import com.baseProject.cafofo.service.OfferService;
import com.baseProject.cafofo.user.Role;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
public class OfferServiceImpl implements OfferService {

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
    @Autowired
    PasswordEncoder passwordEncoder;

    public Collection<OfferDto> findAll(){
        return (Collection<OfferDto>)listMapper.mapList(offerRepo.findAll(), new OfferDto());
    }

    public OfferDto findById(long offerId){
        return modelMapper.map(offerRepo.findById(offerId)
                .orElseThrow(()->new OfferException("Offer not found with id" + offerId)), OfferDto.class);
    }

//    @Transactional
//    public void save(OfferRequest offerRequest){
//        Customer customer = customerRepo.findById(offerRequest.getCustomerId())
//                .orElseThrow(()->new CustomerException("Customer not found with id"+offerRequest.getCustomerId()));
//        Property property = propertyRepo.findById(offerRequest.getPropertyId())
//                .orElseThrow(()->new PropertyException("Property not found with id" + offerRequest.getPropertyId()));
//        Offer offer = new Offer();
//        offer.setCustomer(customer);
//        offer.setOfferPrice(offerRequest.getOfferPrice());
//        offer.setOfferDate(LocalDateTime.now());
//        offer.setOfferStatus(OfferStatus.PENDING);
//        offer.setProperty(property);
//        offerRepo.save(offer);
//    }

    public void update(long offerId, OfferStatusRequest offerStatus){
        Offer offer = offerRepo.findById(offerId)
                .orElseThrow(()->new OfferException("Offer not found with id: "+offerId));
        offer.setOfferStatus(offerStatus.getOfferStatus());
        offerRepo.save(offer);
    }

    public void delete(long offerId){
        Offer offer = offerRepo.findById(offerId)
                .orElseThrow(()->new OfferException("Offer not found with id: "+offerId));
        offer.setOfferStatus(OfferStatus.REJECTED);
        offerRepo.save(offer);

    }

    @Override
    public Collection<OfferDto> findAllById(Long userId) {
        return (Collection<OfferDto>)listMapper.mapList(offerRepo.findAllById(userId), new OfferDto());
    }
}
