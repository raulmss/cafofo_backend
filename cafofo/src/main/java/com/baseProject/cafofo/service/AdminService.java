package com.baseProject.cafofo.service;

import com.baseProject.cafofo.dto.CustomerDto;
import com.baseProject.cafofo.dto.OfferRequest;
import com.baseProject.cafofo.dto.PropertyDto;
import com.baseProject.cafofo.dto.UserDto;

import java.util.Collection;

public interface AdminService {


    Collection<UserDto> findAllCustomers();

    Collection<UserDto> findAllOwners();

    Collection<PropertyDto> findPropertiesTOBeApproved();

    void updateCustomer(Long customerId);

    void updateOwner(Long ownerId);

    void approveProperty(Long propertyId);
  
    String resetUserPassword(long userId, String newPassword);
  
    String userChangeUserPassword(String email, String answer, String newPassword);
  
    String changeActiveStatus(long userId);

    Collection<UserDto> findAllUsers();
}
