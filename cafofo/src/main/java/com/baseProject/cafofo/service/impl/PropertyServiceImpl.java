package com.baseProject.cafofo.service.impl;

import com.baseProject.cafofo.dto.ImageDto;
import com.baseProject.cafofo.dto.PropertyCriteriaRequest;
import com.baseProject.cafofo.dto.PropertyDto;
import com.baseProject.cafofo.entity.PropImage;
import com.baseProject.cafofo.entity.Property;
import com.baseProject.cafofo.help.ListMapper;
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
@Transactional
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    PropertyRepo propertyRepo;
    @Autowired
    ListMapper listMapper;
    @Autowired
    PropImageRepo propImageRepo;
    @Autowired
    PropertyMinMaxSearchDao propertyMinMaxSearchDao;

    public Collection<PropertyDto> findAll(){
        return listMapper.mapList(propertyRepo.findAll(), new PropertyDto());
    }

    public PropertyDto findAllById(Long ownerId,Long propertyId){
        ModelMapper modelMapper = new ModelMapper();
        System.out.println("service "+ propertyId);
        return modelMapper.map(propertyRepo.findPropertyByOwnerEquals(ownerId,propertyId),PropertyDto.class);
    }
@Transactional
    public void save(PropertyDto p){
        List<ImageDto> imageList = new ArrayList<>();
        for(ImageDto i: p.getImage()){
            i.setPath("C:/PropertyPhoto/"+i.getPath());
            imageList.add(i);
            System.out.println(i.getPath());
        }
        p.setImage(imageList);

        ModelMapper modelMapper = new ModelMapper();
        Property property = modelMapper.map(p,Property.class);
        propertyRepo.save(property);
        //return modelMapper.map(propertyRepo.save(property),PropertyDto.class);
    }

    public PropertyDto update (Long propertyId,Property p){
        if(propertyRepo.findById(propertyId).get()!= null){
            for(PropImage i: p.getImage()){
                propImageRepo.delete(i);
            }
//            ModelMapper modelMapper = new ModelMapper();
//            Property property = modelMapper.map(p,PropertyDto.class);
            //return save(p);
            return null;
        }
        return null;
    }

    public void delete(Long propertyId){
        Property p =propertyRepo.findById(propertyId).get();
        if(p != null){
           if (propertyRepo.findPropertyByOffers(propertyId) ==0){
               propertyRepo.delete(p);
           }
        }
    }

    @Override
    public Collection<PropertyDto> searchMinMaxProperty(String dealType, Double minPrice, Double maxPrice,
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

        return listMapper.mapList(propertyMinMaxSearchDao.findAllByCriteria(propertyCriteriaRequest),PropertyDto.class) ;
    }


}
