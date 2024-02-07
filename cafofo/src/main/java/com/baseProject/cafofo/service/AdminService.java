package com.baseProject.cafofo.service;

import com.baseProject.cafofo.dto.CustomerDto;
import com.baseProject.cafofo.dto.OfferRequest;
import com.baseProject.cafofo.dto.UserDto;

import java.util.Collection;

public interface AdminService {


    Collection<UserDto> findAllCustomers();

    Collection<UserDto> findAllOwners();

    void updateCustomer(Long customerId);

    void updateOwner(Long ownerId);

    void approveProperty(Long propertyId);
}
