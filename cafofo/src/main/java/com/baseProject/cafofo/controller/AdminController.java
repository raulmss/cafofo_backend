package com.baseProject.cafofo.controller;

import com.baseProject.cafofo.config.URLConstants;
import com.baseProject.cafofo.dto.UserDto;
import com.baseProject.cafofo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(URLConstants.ADMIN_ENDPOINTS)
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/customers")
    Collection<UserDto> findAllCustomers(){
        System.out.println("inside findAllCustomers method controller");
        return adminService.findAllCustomers();
    }

    @GetMapping("/owners")
    Collection<UserDto> findAllOwners(){
        System.out.println("inside findAllOwners method controller");
        return adminService.findAllOwners();
    }

    //change customer inactive
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/customers/{id}")
    void updateCustomer(@PathVariable("id") Long customerId){
        System.out.println("inside updateCustomer method controller");
        adminService.updateCustomer(customerId);
    }

    //change owner inactive
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/owners/{id}")
    void updateOwner(@PathVariable("id") Long ownerId){
        System.out.println("inside updateOwner method controller");
        adminService.updateOwner(ownerId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("approve-owner-property/{id}")
    void approveProperty(@PathVariable("id") Long propertyId){
        System.out.println("inside approveProperty method controller");
        adminService.approveProperty(propertyId);
    }





}
