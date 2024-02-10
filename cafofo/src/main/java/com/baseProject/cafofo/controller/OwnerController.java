package com.baseProject.cafofo.controller;

import com.baseProject.cafofo.dto.PropertyDto;
import com.baseProject.cafofo.entity.DealType;
import com.baseProject.cafofo.entity.Property;
import com.baseProject.cafofo.service.OwnerService;
import com.baseProject.cafofo.service.PropertyService;
import com.baseProject.cafofo.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.baseProject.cafofo.user.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/owners")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OwnerController {
    @Autowired
    OwnerService ownerService;
    @Autowired
    PropertyService propertyService;
    @Value("${upload_download.directory}")
    private String dir;

    @Value("${prefixdir}")
    private String prefix;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/properties")
    @PreAuthorize("hasAuthority('OWNER')")
    @ResponseStatus(HttpStatus.OK)
    public Collection<PropertyDto> getOwnerProperties(@RequestParam(value ="dealtype", required = false) String dealtype){

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //if owner, then fav icon cannot show (talk to cyrus)
        if(dealtype == null){
            return propertyService.findAllPropertyByOwner(user.getId());
        }else{
            System.out.println("<<contrller>>"+dealtype);
            DealType dealType = DealType.valueOf(dealtype.toUpperCase());
            return propertyService.findAllPropertyByOwnerWithDealType(user.getId(), dealType);
        }
    }

    @GetMapping("/{ownerid}/filter")
    @PreAuthorize("hasAuthority('OWNER')")
    @ResponseStatus(HttpStatus.OK)
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

    @GetMapping("/{ownerid}/filteraddress")
    @PreAuthorize("hasAuthority('OWNER')")
    @ResponseStatus(HttpStatus.OK)
    public List<Property> searchPropertyByAddress(
            @PathVariable("ownerid") Long ownerid,
            @RequestParam(value ="homenumber", required = false) String homenumber,
            @RequestParam(value ="street", required = false) String street,
            @RequestParam(value ="city", required = false) String city,
            @RequestParam(value ="state", required = false) String state,
            @RequestParam(value ="country", required = false) String country,
            @RequestParam(value ="zip", required = false) String zip
    )    {

        return propertyService.searchAddressOwner(ownerid,country,state,city,street,homenumber,zip);
    }


  
     @GetMapping("/{ownerId}/properties/{propertiesId}/offers")
     @PreAuthorize("hasAuthority('OWNER')")
     Collection<OfferDto> findOffersByPropertiesId(@PathVariable("ownerId") Long ownerId, @PathVariable("propertiesId") Long propertiesId){

        return ownerService.findOffersByPropertiesId(ownerId, propertiesId);
    }

    //owner accept or reject offer
    ///{ownerId}/properties/{propertiesId}/offers/{offerId}?status=accepted
    ///{ownerId}/properties/{propertiesId}/offers/{offerId}?status=rejected
    @GetMapping("/{ownerId}/properties/{propertiesId}/offers/{offerId}")
    @PreAuthorize("hasAuthority('OWNER')")
    void approveOffer(@PathVariable("ownerId") Long ownerId,
                      @PathVariable("propertiesId") Long propertiesId,
                      @PathVariable("offerId") Long offerId,
                      @RequestParam("status") String status){

        if(status.equalsIgnoreCase(String.valueOf(OfferStatus.ACCEPTED))){

            ownerService.approveOffer(ownerId,propertiesId,offerId);
        }else if(status.equalsIgnoreCase(String.valueOf(OfferStatus.REJECTED))){

            ownerService.rejectOffer(ownerId,propertiesId,offerId);
        }else{
            throw new RuntimeException("status is not allowed");
        }
    }

    @GetMapping("/{ownerid}/properties/{propid}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('OWNER')")
    public PropertyDto findPropertyDetailByOwner(@PathVariable("ownerid") Long ownerid, @PathVariable("propid") Long propid){
        System.out.println("contorller owner <<>>"+ ownerid+ propid);
        return propertyService.findPropertyDetailByOwner(ownerid,propid);
    }
    @PostMapping("/{ownerid}/upload")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('OWNER')")
    public ResponseEntity<List<String>> uploadFiles(Long ownerid, @RequestParam("files") List<MultipartFile> multipartFiles){
        System.out.println("Contorller file upload: "+ ownerid);
        List<String> filenames = new ArrayList<>();

        // Create a Path object for the directory

        Path directory = Paths.get(System.getProperty(this.prefix), this.dir);
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

        propertyService.save(property);
    }

    @GetMapping("/{ownerId}/offers")
    @PreAuthorize("hasAuthority('OWNER')")
    Collection<OfferDto> findOfferByOwnerId(@PathVariable("ownerId") Long ownerId){

        return ownerService.findOfferByOwnerId(ownerId)
                .stream()
                .map((element) -> modelMapper.map(element, OfferDto.class))
                .collect(Collectors.toList());
    }
}
