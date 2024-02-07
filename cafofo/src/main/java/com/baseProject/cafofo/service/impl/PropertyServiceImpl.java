package com.baseProject.cafofo.service.impl;

import com.baseProject.cafofo.dto.ImageDto;
import com.baseProject.cafofo.dto.PropertyCriteriaRequest;
import com.baseProject.cafofo.dto.PropertyDto;
import com.baseProject.cafofo.entity.PropImage;
import com.baseProject.cafofo.entity.Property;
import com.baseProject.cafofo.entity.PropertyStatus;
import com.baseProject.cafofo.helper.ListMapper;
import com.baseProject.cafofo.repo.PropImageRepo;
import com.baseProject.cafofo.repo.PropertyMinMaxSearchDao;
import com.baseProject.cafofo.repo.PropertyRepo;
import com.baseProject.cafofo.service.PropertyService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class PropertyServiceImpl implements PropertyService {
    @Autowired
    PropertyRepo propertyRepo;
    @Autowired
    ListMapper listMapper;
    @Autowired
    PropImageRepo propImageRepo;
    @Autowired
    PropertyMinMaxSearchDao propertyMinMaxSearchDao;

    public Collection<PropertyDto> findAll(Long ownerid){
        Collection<Property> properties = propertyRepo.findAllPropertyByOwner(ownerid);
        Collection<PropertyDto> propertyDtos = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (Property property : properties) {
            PropertyDto propertyDto = modelMapper.map(property, PropertyDto.class);
            if(property.getApprovalStatus() != false){
                propertyDto.setPropertyStatus(property.getPropertyStatus());
            }
            else {
                propertyDto.setPropertyStatus(PropertyStatus.INITIAL);
            }

            propertyDtos.add(propertyDto);
        }
        return propertyDtos;
    }

    public PropertyDto findAllById(Long ownerId,Long propertyId){
        ModelMapper modelMapper = new ModelMapper();
        Property property = propertyRepo.findPropertyByOwnerEquals(ownerId,propertyId);
        PropertyDto propertyDto = modelMapper.map(property,PropertyDto.class);
        System.out.println("<<>>"+ property.getApprovalStatus());;
        if(property.getApprovalStatus() == false){
            propertyDto.setPropertyStatus(PropertyStatus.INITIAL);
        }
        else {
            propertyDto.setPropertyStatus(property.getPropertyStatus());
        }
        System.out.println("service "+ propertyId);
        return propertyDto;
    }
@Transactional
    public void save(PropertyDto p){
        Collection<ImageDto> imageList = new ArrayList<>();
        for(ImageDto i: p.getImage()){
            i.setPath("C:/PropertyPhoto/"+i.getPath());
            imageList.add(i);
            System.out.println(i.getPath());
        }
        p.setImage(imageList);
        p.setApprovalStatus(false);

        ModelMapper modelMapper = new ModelMapper();
        Property property = modelMapper.map(p,Property.class);
        propertyRepo.save(property);

    }

    public void update (Long propertyId,PropertyDto p){
        ModelMapper modelMapper = new ModelMapper();
        Property property =propertyRepo.findById(propertyId).get();
        if(property!= null){
            for(PropImage i: property.getImage()){
                propImageRepo.delete(i);
            }
            save(p);
        }

    }
    public void delete(Long propertyId){
        Property p =propertyRepo.findById(propertyId).get();
        System.out.println("delete...");
        System.out.println(propertyRepo.findPropertyByOffers(propertyId) +"<<>>");
        if(p != null){
           if (propertyRepo.findPropertyByOffers(propertyId) ==null){

               propertyRepo.delete(p);
           }
        }
    }

    @Override
    public List<Property> searchMinMaxProperty(String dealType, Double minPrice, Double maxPrice,
                                                  Double numBed, Double numBath, String homeType,
                                                  Double minArea, Double maxArea, String factAndFactory) {
        PropertyCriteriaRequest propertyCriteriaRequest = new PropertyCriteriaRequest();
        propertyCriteriaRequest.setDealType(dealType);
        propertyCriteriaRequest.setMinPrice(minPrice);
        propertyCriteriaRequest.setMaxPrice(maxPrice);
        propertyCriteriaRequest.setHomeType(homeType);
        propertyCriteriaRequest.setNumBath(numBath);
        propertyCriteriaRequest.setNumBed(numBed);
        propertyCriteriaRequest.setFactAndFactory(factAndFactory);
        propertyCriteriaRequest.setMinArea(minArea);
        propertyCriteriaRequest.setMaxArea(maxArea);

        return propertyMinMaxSearchDao.findAllByCriteria(propertyCriteriaRequest) ;
    }

    @Override
    public Collection<PropertyDto> findPropertyByCustomer() {
        Collection<Property> properties = propertyRepo.findAll();
        Collection<PropertyDto> propertyDtos = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (Property property : properties) {
            PropertyDto propertyDto = modelMapper.map(property, PropertyDto.class);
            if(property.getApprovalStatus() != false){
                propertyDto.setPropertyStatus(property.getPropertyStatus());
            }
            else {
                propertyDto.setPropertyStatus(PropertyStatus.INITIAL);
            }

            propertyDtos.add(propertyDto);
        }
        return propertyDtos;
    }

    @Override
    public PropertyDto findPropertyByCustomer(Long propid) {
        ModelMapper modelMapper = new ModelMapper();
        Property property = propertyRepo.findById(propid).get();
        PropertyDto propertyDto = modelMapper.map(property,PropertyDto.class);
        System.out.println("<<>>"+ property.getApprovalStatus());;
        if(property.getApprovalStatus() == false){
            propertyDto.setPropertyStatus(PropertyStatus.INITIAL);
        }
        else {
            propertyDto.setPropertyStatus(property.getPropertyStatus());
        }
        System.out.println("service "+ propid);
        return propertyDto;
    }


}
