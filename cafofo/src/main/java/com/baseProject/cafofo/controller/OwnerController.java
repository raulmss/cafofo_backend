package com.baseProject.cafofo.controller;

import com.baseProject.cafofo.dto.PropertyDto;
import com.baseProject.cafofo.entity.Property;
import com.baseProject.cafofo.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baseProject.cafofo.config.URLConstants;
import com.baseProject.cafofo.dto.OfferDto;
import com.baseProject.cafofo.dto.OfferRequest;
import com.baseProject.cafofo.dto.OfferStatusRequest;
import com.baseProject.cafofo.entity.OfferStatus;
import com.baseProject.cafofo.service.CustomerService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/v1/owners")
public class OwnerController {
    @Autowired
    OwnerService ownerService;

    @GetMapping("/{ownerid}/properties")
    public Collection<PropertyDto> getOwnerPropertiesByPlaced(@PathVariable ("ownerid") Long userId){
        System.out.println("Owner selection");
        return ownerService.getOwnerPropertiesByPlaced(userId);
    }

    @GetMapping("/{ownerid}/filter")
    public List<Property> searchEqualProperty(
            @PathVariable("ownerid") Long ownerid,
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

        return ownerService.searchOwnerMinMaxProperty(ownerid,dealType,minPrice,maxPrice,numBed,numBath,
                homeType,minArea,maxArea,factAndFactory);
    }
  
     @GetMapping("/{ownerId}/properties/{propertiesId}/offers")
    Collection<OfferDto> findOffersByPropertiesId(@PathVariable("ownerId") Long ownerId, @PathVariable("propertiesId") Long propertiesId){
        System.out.println("inside findOffersByPropertiesId method controller");
        return ownerService.findOffersByPropertiesId(ownerId, propertiesId);
    }

    @GetMapping("/{ownerId}/properties/{propertiesId}/offers/{offerId}")
    void approveOffer(@PathVariable("ownerId") Long ownerId,
                      @PathVariable("propertiesId") Long propertiesId,
                      @PathVariable("offerId") Long offerId,
                      @RequestParam("status") String status){
        System.out.println("STATUS: ");
        System.out.println("inside approveOffer controller");
        if(status.equalsIgnoreCase(String.valueOf(OfferStatus.ACCEPTED))){
            System.out.println("to approve");
            ownerService.approveOffer(ownerId,propertiesId,offerId);
        }else if(status.equalsIgnoreCase(String.valueOf(OfferStatus.REJECTED))){
            System.out.println("to reject");
            ownerService.rejectOffer(ownerId,propertiesId,offerId);
        }else{
            throw new RuntimeException("status is not allowed");
        }
    }

}
