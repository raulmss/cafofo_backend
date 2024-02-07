package com.baseProject.cafofo.controller;

import com.baseProject.cafofo.config.URLConstants;
import com.baseProject.cafofo.dto.OfferDto;
import com.baseProject.cafofo.dto.OfferRequest;
import com.baseProject.cafofo.dto.OfferStatusRequest;
import com.baseProject.cafofo.entity.OfferStatus;
import com.baseProject.cafofo.service.CustomerService;
import com.baseProject.cafofo.service.OwnerService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(URLConstants.OWNER_ENDPOINTS)
@CrossOrigin(origins = "http://localhost:3000")
public class OwnerController {

    @Autowired
    OwnerService ownerService;

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
