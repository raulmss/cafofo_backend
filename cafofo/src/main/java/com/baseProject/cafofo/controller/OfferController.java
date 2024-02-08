package com.baseProject.cafofo.controller;

import com.baseProject.cafofo.config.URLConstants;
import com.baseProject.cafofo.dto.OfferDto;
import com.baseProject.cafofo.dto.OfferRequest;
import com.baseProject.cafofo.dto.OfferStatusRequest;
import com.baseProject.cafofo.entity.OfferStatus;
import com.baseProject.cafofo.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(URLConstants.OFFER_ENDPOINTS)
@CrossOrigin(origins = "*")
public class OfferController {

    @Autowired
    OfferService offerService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    Collection<OfferDto> findAll(){
        return offerService.findAll();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER')")
    @GetMapping("/{id}")
    OfferDto findById(@PathVariable("id") long offerId){
        return offerService.findById(offerId);
    }


//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping
//    void save(@RequestBody OfferRequest offerRequest){
//        System.out.println("inside save method controller");
//        offerService.save(offerRequest);
//    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    void update(@PathVariable("id")long offerId, @RequestBody OfferStatusRequest offerStatus){
        offerService.update(offerId, offerStatus);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") long offerId){
        offerService.delete(offerId);
    }



}
