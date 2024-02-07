package com.baseProject.cafofo.controller;

import com.baseProject.cafofo.dto.PropertyDto;
import com.baseProject.cafofo.entity.Property;
import com.baseProject.cafofo.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {
    @Autowired
    PropertyService propertyService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('OWNER') || hasAuthority('CUSTOMER')")
    public Collection<PropertyDto> findPropertyByCustomer(){
        return propertyService.findPropertyByCustomer();
    }

    @GetMapping("/{propid}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('OWNER') || hasAuthority('CUSTOMER')")
    public PropertyDto findPropertyByCustomer( @PathVariable("propid") Long propid){
        return propertyService.findPropertyByCustomer(propid);
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
