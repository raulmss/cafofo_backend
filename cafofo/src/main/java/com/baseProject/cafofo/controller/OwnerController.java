package com.baseProject.cafofo.controller;

import com.baseProject.cafofo.dto.PropertyDto;
import com.baseProject.cafofo.entity.Property;
import com.baseProject.cafofo.service.OwnerService;
import com.baseProject.cafofo.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.baseProject.cafofo.config.URLConstants;
import com.baseProject.cafofo.dto.OfferDto;
import com.baseProject.cafofo.dto.OfferRequest;
import com.baseProject.cafofo.dto.OfferStatusRequest;
import com.baseProject.cafofo.entity.OfferStatus;
import com.baseProject.cafofo.service.CustomerService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/v1/owners")
public class OwnerController {
    @Autowired
    OwnerService ownerService;
    @Autowired
    PropertyService propertyService;

    @GetMapping("/{ownerid}/properties")
    @PreAuthorize("hasAuthority('OWNER')")
    public Collection<PropertyDto> getOwnerPropertiesByPlaced(@PathVariable ("ownerid") Long userId){
        System.out.println("Owner selection");
        return ownerService.getOwnerPropertiesByPlaced(userId);
    }

    @GetMapping("/{ownerid}/filter")
    @PreAuthorize("hasAuthority('OWNER')")
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
     @PreAuthorize("hasAuthority('OWNER')")
     Collection<OfferDto> findOffersByPropertiesId(@PathVariable("ownerId") Long ownerId, @PathVariable("propertiesId") Long propertiesId){
        System.out.println("inside findOffersByPropertiesId method controller");
        return ownerService.findOffersByPropertiesId(ownerId, propertiesId);
    }

    @GetMapping("/{ownerId}/properties/{propertiesId}/offers/{offerId}")
    @PreAuthorize("hasAuthority('OWNER')")
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

    @GetMapping("/{ownerid}/properities")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('OWNER')")
    public Collection<PropertyDto> findAll(@PathVariable("ownerid") Long ownerid){
        return propertyService.findAll(ownerid);
    }

    @GetMapping("/{ownerid}/properities/{propid}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('OWNER')")
    public PropertyDto findById(@PathVariable("ownerid") Long ownerid, @PathVariable("propid") Long propid){
        System.out.println("Controller "+ ownerid+" " +propid);
        return propertyService.findAllById(ownerid,propid);
    }
    @PostMapping("/{ownerid}/upload")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('OWNER')")
    public ResponseEntity<List<String>> uploadFiles(Long ownerid, @RequestParam("files") List<MultipartFile> multipartFiles){
        List<String> filenames = new ArrayList<>();
        System.out.println("before path");
        // Create a Path object for the directory
        Path directory = Paths.get("C:/PropertyPhoto/");
        System.out.println("file uploadFiles method"+ directory);
        try{
            // Create directory if it does not exist
            if (!Files.exists(directory)) {
                Files.createDirectory(directory);
            }
            for(MultipartFile file : multipartFiles){
                if(file.isEmpty()){
                    System.out.println("File not found");
                }
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                // Create a Path object for the destination file
                Path destinationPath = Paths.get(directory.toString(), fileName);
                // Copy file to destination
                Files.copy(file.getInputStream(), destinationPath);
                filenames.add(fileName);
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return ResponseEntity.ok().body(filenames);
    }

    @PostMapping("/{ownerId}/properties")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('OWNER')")
    public void save(Long ownerId,@RequestBody PropertyDto property){
        System.out.println("Property Controller");
        propertyService.save(property);
    }

}
