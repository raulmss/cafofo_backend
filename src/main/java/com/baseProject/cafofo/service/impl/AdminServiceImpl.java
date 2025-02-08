package com.baseProject.cafofo.service.impl;

import com.baseProject.cafofo.dto.PropertyDto;
import com.baseProject.cafofo.dto.UserDto;
import com.baseProject.cafofo.entity.Customer;
import com.baseProject.cafofo.entity.Owner;
import com.baseProject.cafofo.entity.Property;
import com.baseProject.cafofo.exceptions.PropertyException;
import com.baseProject.cafofo.exceptions.UserNotFoundException;
import com.baseProject.cafofo.helper.ListMapper;
import com.baseProject.cafofo.repo.*;
import com.baseProject.cafofo.service.AdminService;
import com.baseProject.cafofo.user.Role;
import com.baseProject.cafofo.user.User;
import com.baseProject.cafofo.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ListMapper listMapper;

    @Autowired
    AdminRepo adminRepo;
    @Autowired
    UserRepository userRepo;

    @Autowired
    PropertyRepo propertyRepo;
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    OwnerRepo ownerRepo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Collection<UserDto> findAllCustomers(){

        return listMapper.mapList(adminRepo.findByUser(Role.CUSTOMER),new UserDto());
    }

    public Collection<UserDto> findAllOwners(){
        return listMapper.mapList(adminRepo.findByUser(Role.OWNER),new UserDto());
    }

    @Override
    public Collection<PropertyDto> findPropertiesTOBeApproved() {
        return propertyRepo.findByApprovalStatus(false).stream()
                .map(property -> modelMapper.map(property, PropertyDto.class))
                .toList();
    }

    //update customers inactive
    public void updateCustomer(Long customerId){
        //customerId is userId;
       Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new UserNotFoundException("Customer not found with id: " + customerId));
        User user = userRepo.findById(customerId)
                .orElseThrow(()->new UserNotFoundException("Customer not found with id: "+customerId));
        user.setActive(!user.isActive());
        userRepo.save(user);
    }

    public void updateOwner(Long ownerId){
        //ownerId is userId;
        Owner owner = ownerRepo.findById(ownerId)
                .orElseThrow(() -> new UserNotFoundException("Owner not found with id: " + ownerId));
        User user = userRepo.findById(ownerId)
                .orElseThrow(()->new UserNotFoundException("Owner not found with id: "+ownerId));
        user.setActive(!user.isActive());
        userRepo.save(user);
    }

    public void approveProperty(Long propertyId){
        Property property = propertyRepo.findById(propertyId)
                .orElseThrow(()->new PropertyException("Property not found with id: "+propertyId));
        property.setApprovalStatus(true);
        propertyRepo.save(property);
    }

    @Override
    public String resetUserPassword(long userId, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        return "Password changed successfully";
    }
    //    TODO: add get all users

    @Override
    public String userChangeUserPassword(String email, String answer, String newPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (passwordEncoder.matches(answer, user.getSecretAnswer())){
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return "Password changed successfully";
        }
        return "Something went wrong, please try again later.";
    }

    @Override
    public String changeActiveStatus(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setActive(!user.isActive());
        userRepository.save(user);

        return user.isActive()? "User is now active" : "User is now inactive";
    }

    @Override
    public Collection<UserDto> findAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .toList();
    }

}
