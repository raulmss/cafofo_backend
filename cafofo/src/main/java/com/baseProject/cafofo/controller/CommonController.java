package com.baseProject.cafofo.controller;

import com.baseProject.cafofo.dto.PropertyDto;
import com.baseProject.cafofo.entity.DTO.ChangePassWordDTO;
import com.baseProject.cafofo.entity.DealType;
import com.baseProject.cafofo.entity.Property;
import com.baseProject.cafofo.service.AdminService;
import com.baseProject.cafofo.service.PropertyService;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/commom")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CommonController {

    private final AdminService adminService;
    private final PropertyService propertyService;

    @GetMapping("/properties")
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

        return propertyDtos.stream().filter(PropertyDto::getApprovalStatus).collect(Collectors.toList());

    }

    @GetMapping("properties/filter")
    @ResponseStatus(HttpStatus.OK)
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
    )  {

        return propertyService.searchMinMaxProperty(dealType,minPrice,maxPrice,numBed,numBath,
                homeType,minArea,maxArea,factAndFactory);
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ChangePassWordDTO changePassWordDTO){
        return ResponseEntity.ok(adminService
                .userChangeUserPassword(changePassWordDTO.getEmail(),
                changePassWordDTO.getSecretAnswer(),
                changePassWordDTO.getPassword()));
    }
}
