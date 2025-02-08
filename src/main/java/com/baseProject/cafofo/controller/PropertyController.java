package com.baseProject.cafofo.controller;

import com.baseProject.cafofo.dto.OwnerDto;
import com.baseProject.cafofo.dto.PropertyDto;
import com.baseProject.cafofo.entity.DealType;
import com.baseProject.cafofo.entity.Owner;
import com.baseProject.cafofo.entity.Property;
import com.baseProject.cafofo.service.OwnerService;
import com.baseProject.cafofo.service.PropertyService;
import com.baseProject.cafofo.user.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;

import com.baseProject.cafofo.user.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/api/v1/properties")
@CrossOrigin(origins = "*")
public class PropertyController {
    @Autowired
    PropertyService propertyService;
    @Autowired
    OwnerService ownerService;

    @Autowired
    ModelMapper modelMapper;

    @Value("${upload_download.directory}")
    private String dir;

    @Value("${prefixdir}")
    private String prefix;

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

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Owner owner = ownerService.findById(user.getId());
        property.setOwner(modelMapper.map(owner, OwnerDto.class));
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
    )  {

        return propertyService.searchMinMaxProperty(dealType,minPrice,maxPrice,numBed,numBath,
        homeType,minArea,maxArea,factAndFactory);
    }

    @GetMapping("/filteraddressguest")
    @ResponseStatus(HttpStatus.OK)
    public List<Property> searchAddressByGuest(
            @RequestParam(value ="homenumber", required = false) String homenumber,
            @RequestParam(value ="street", required = false) String street,
            @RequestParam(value ="city", required = false) String city,
            @RequestParam(value ="state", required = false) String state,
            @RequestParam(value ="country", required = false) String country,
            @RequestParam(value ="zip", required = false) String zip

    )  {
        return propertyService.searchAddressGuest(country,state,city,street,homenumber,zip);
    }

    @GetMapping("/filteraddresscustomer")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public Collection<PropertyDto> searchAddressByCustomer(
            @RequestParam(value ="homenumber", required = false) String homenumber,
            @RequestParam(value ="street", required = false) String street,
            @RequestParam(value ="city", required = false) String city,
            @RequestParam(value ="state", required = false) String state,
            @RequestParam(value ="country", required = false) String country,
            @RequestParam(value ="zip", required = false) String zip

    )  {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return propertyService.searchAddressCustomer(user.getId(),country,state,city,street,homenumber,zip);
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

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('OWNER')")
    public void save(@RequestBody PropertyDto property){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Owner owner = ownerService.findById(user.getId());
        property.setOwner(modelMapper.map(owner, OwnerDto.class));
        propertyService.save(property);
    }


}
