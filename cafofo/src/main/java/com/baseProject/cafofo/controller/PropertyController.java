package com.baseProject.cafofo.controller;

import com.baseProject.cafofo.dto.PropertyDto;
import com.baseProject.cafofo.entity.DealType;
import com.baseProject.cafofo.entity.Property;
import com.baseProject.cafofo.service.PropertyService;
import com.baseProject.cafofo.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;

import com.baseProject.cafofo.user.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/api/v1/properties")
@CrossOrigin(origins = "*")
public class PropertyController {
    @Autowired
    PropertyService propertyService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Collection<PropertyDto> findPropertyByGuest(@RequestParam (value ="dealtype", required = false) String dealtype){
        Collection<PropertyDto> propertyDtos = new ArrayList<>();
        //make it endpoint permit all
        //If make an action to each role
        //If customer, populate isFav according to customer favorite lists
        //If Guest(anonimous),isFav is false
//        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString() == "anonymousUser"){
            if(dealtype == null){
                propertyDtos = propertyService.findPropertyByGuest();
            }else{
                propertyDtos = propertyService.findPropertyByGuestWithDealType(DealType.valueOf(dealtype));
            }

        return propertyDtos;
    }

    @GetMapping("/customers")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public Collection<PropertyDto> findPropertyByCustomer(@RequestParam (value ="dealtype", required = false) String dealtype){
        Collection<PropertyDto> propertyDtos = new ArrayList<>();
        //make it endpoint permit all
        //If make an action to each role
        //If customer, populate isFav according to customer favorite lists
        //If Guest(anonimous),isFav is false

            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(user.getRole() == Role.CUSTOMER){
                if(dealtype !=null){
                    propertyDtos = propertyService.findPropertyByCustomerByDealType(user.getId(), DealType.valueOf(dealtype));
                }else{
                    propertyDtos = propertyService.findPropertyByCustomer(user.getId());
                }
        }

        return propertyDtos;
    }
    @GetMapping("/{propid}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('OWNER') || hasAuthority('CUSTOMER')")
    public PropertyDto findPropertyDetail( @PathVariable("propid") Long propid){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return propertyService.findPropertyDetail(propid, user.getId());
    }

    @DeleteMapping("/{propertyId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('OWNER') || hasAuthority('CUSTOMER')")
    public void delete(@PathVariable ("propertyId") Long propertyId){
        propertyService.delete(propertyId);
    }

    @PutMapping("/{propertyId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('OWNER') || hasAuthority('CUSTOMER')")
    public void update(@PathVariable ("propertyId") Long propertyId, @RequestBody PropertyDto property){
        System.out.println("Controller update");
        propertyService.update(propertyId,property);
    }

    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('OWNER') || hasAuthority('CUSTOMER')")

    public List<Property> searchEqualProperty(
        @RequestParam(value ="dealtype", required = false) String dealType,
        @RequestParam(value ="minprice", required = false) Double minPrice,
        @RequestParam(value ="maxprice", required = false) Double maxPrice,
        @RequestParam(value ="numbed", required = false) Double numBed,
        @RequestParam(value ="numbath", required = false) Double numBath,
        @RequestParam(value ="hometype", required = false) String homeType,
        @RequestParam(value ="minarea", required = false) Double minArea,
        @RequestParam(value ="maxarea", required = false) Double maxArea,
        @RequestParam(value ="factandfactory", required = false) String factAndFactory
    )    {

        return propertyService.searchMinMaxProperty(dealType,minPrice,maxPrice,numBed,numBath,
        homeType,minArea,maxArea,factAndFactory);
    }



}
