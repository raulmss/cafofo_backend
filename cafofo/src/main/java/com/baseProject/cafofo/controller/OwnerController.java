package com.baseProject.cafofo.controller;

import com.baseProject.cafofo.dto.PropertyDto;
import com.baseProject.cafofo.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

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
    public Collection<PropertyDto> searchEqualProperty(
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

}
