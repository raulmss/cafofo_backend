package com.baseProject.cafofo.controller;

import com.baseProject.cafofo.dto.PropertyDto;
import com.baseProject.cafofo.entity.Property;
import com.baseProject.cafofo.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/v1")
public class PropertyController {
    @Autowired
    PropertyService propertyService;

    @GetMapping("/owner/{ownerid}/properities")
    @ResponseStatus(HttpStatus.OK)
    public Collection<PropertyDto> findAll(@PathVariable("ownerid") Long ownerid){
        return propertyService.findAll(ownerid);
    }

    @GetMapping("/owner/{ownerid}/properities/{propid}")
    @ResponseStatus(HttpStatus.OK)
    public PropertyDto findById(@PathVariable("ownerid") Long ownerid, @PathVariable("propid") Long propid){
        System.out.println("Controller "+ ownerid+" " +propid);
        return propertyService.findAllById(ownerid,propid);
    }
    @PostMapping("/{ownerid}/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<List<String>> uploadFiles(Long ownerid,@RequestParam("files") List<MultipartFile> multipartFiles){
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
    public void save(Long ownerId,@RequestBody PropertyDto property){
        System.out.println("Property Controller");
         propertyService.save(property);
    }

    @DeleteMapping("/{propertyId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable ("propertyId") Long propertyId){
        propertyService.delete(propertyId);
    }

    @PutMapping("/{propertyId}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable ("propertyId") Long propertyId, @RequestBody PropertyDto property){
        System.out.println("Controller update");
        propertyService.update(propertyId,property);
    }

    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public Collection<PropertyDto> searchEqualProperty(
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
