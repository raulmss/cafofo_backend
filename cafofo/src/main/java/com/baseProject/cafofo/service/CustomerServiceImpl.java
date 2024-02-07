package com.baseProject.cafofo.service;

import com.baseProject.cafofo.DTO.FavouriteDto;
import com.baseProject.cafofo.Repository.CustomerRepository;
import com.baseProject.cafofo.Repository.OfferRepository;
import com.baseProject.cafofo.Repository.PropertyRepository;
import com.baseProject.cafofo.entity.Customer;
import com.baseProject.cafofo.entity.Offer;
import com.baseProject.cafofo.entity.OfferStatus;
import com.baseProject.cafofo.entity.Property;
import com.baseProject.cafofo.exceptions.CafofoApplicationException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final PropertyRepository propertyRepository;

    private final ModelMapper modelMapper;

    @Override
    public String addToFavorites(Long userId, Long propertyId) {
        Customer customer = getCustomer(userId);
        Property property = getProperty(propertyId);

        if (customer != null && property != null) {
            // Check if the property is not already in the favorites list
            if (!customer.getFavoriteProperties().contains(property)) {
                customer.getFavoriteProperties().add(property);
                customerRepository.save(customer);
                return "Property added to favorites successfully.";
            } else {
                throw new CafofoApplicationException("Property is already in the favorites list.");
            }
        } else {
            throw new CafofoApplicationException("User or property not found.");
        }
    }

    @Override
    public String removeFromFavorites(Long userId, Long propertyId) {
        Customer customer = getCustomer(userId);
        Property property = getProperty(propertyId);
        if (customer != null && property != null) {
            // Check if the property is in the favorites list
            if (customer.getFavoriteProperties().contains(property)) {
                customer.getFavoriteProperties().remove(property);
                customerRepository.save(customer);
                return "Property remove to favorites successfully.";
            } else {
                throw new CafofoApplicationException("This Property is not in the favorites list.");
            }
        } else {
            throw new CafofoApplicationException("User or property not found.");
        }
    }

    @Override
    public List<FavouriteDto> getFavorites(Long userId) {
        Customer customer = getCustomer(userId);
        List<FavouriteDto> favouriteDtoList = new ArrayList<>();
        if (customer != null) {
            for (Property p : customer.getFavoriteProperties()) {
                FavouriteDto favouriteDto = modelMapper.map(p, FavouriteDto.class);
                favouriteDtoList.add(favouriteDto);
            }
            return favouriteDtoList;
        } else {
            throw new CafofoApplicationException("User not found.");
        }
    }

    private Customer getCustomer(Long userId) {
        return customerRepository.findCustomerByUserId(userId);
    }

    private Property getProperty(Long propertyId) {
        return propertyRepository.findById(propertyId).get();
    }

    @Override
    public List<Offer> getOffersByUser(Long userId) {
        return customerRepository.findOffersByUserId(userId);
    }

    @Override
    public String cancelOffer(Long offerId, Long userId) {

        offerValidation(offerId, userId);
        customerRepository.cancelOffer(offerId, userId);
        return "Offer canceled successfully.";

    }

    @Override
    public String maintainOfferByPrice(Long offerId, double price, Long userId) {
        offerValidation(offerId, userId);
        customerRepository.maintainOfferByPrice(price,offerId, userId);
        return "Offer Price was successfully updated.";
    }

    private void offerValidation(Long offerId, Long userId) {
        Offer offer = customerRepository.checkOffer(userId, offerId);
        if (offer!= null) {
            // Check if the offer status is 'Pending'
            if (!OfferStatus.PENDING.equals(offer.getOfferStatus())) {
                throw new CafofoApplicationException("Unable to cancel the offer. Invalid offer status or user.");
            }
        } else {
            throw new CafofoApplicationException("Offer not found.");
        }

    }

}
