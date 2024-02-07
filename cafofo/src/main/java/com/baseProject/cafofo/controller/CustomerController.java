package com.baseProject.cafofo.controller;

import com.baseProject.cafofo.config.URLConstants;
import com.baseProject.cafofo.dto.OfferRequest;
import com.baseProject.cafofo.dto.OfferRequestDto;
import com.baseProject.cafofo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(URLConstants.CUSTOMER_ENDPOINTS)
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/{id}/offers")
    @ResponseStatus(HttpStatus.CREATED)
    void save(@PathVariable("id") Long customerId, @RequestBody OfferRequestDto offerRequest){
        System.out.println("inside save method controller");
        customerService.save(customerId, offerRequest);
    }



}
