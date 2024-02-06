package com.baseProject.cafofo.service.impl;

import com.baseProject.cafofo.dto.OwnerPropertyCriteriaRequest;
import com.baseProject.cafofo.dto.PropertyDto;
import com.baseProject.cafofo.help.ListMapper;
import com.baseProject.cafofo.repo.OwnerPropertySearchDao;
import com.baseProject.cafofo.repo.OwnerRepo;
import com.baseProject.cafofo.service.OwnerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    OwnerRepo ownerRepo;
    @Autowired
    ListMapper listMapper;
    @Autowired
    OwnerPropertySearchDao ownerPropertySearchDao;

    @Transactional
    public Collection<PropertyDto> getOwnerPropertiesByPlaced(Long ownerId){
        Collection<PropertyDto> propertyDtoList = listMapper.mapList( ownerRepo.getOwnerPropertiesByPlaced(ownerId), new PropertyDto()) ;
        return propertyDtoList;
    }
    @Transactional
    @Override
    public Collection<PropertyDto> searchOwnerMinMaxProperty(Long ownerId, String dealType, Double minPrice,
                                                             Double maxPrice, Double numBed, Double numBath,
                                                             String homeType, Double minArea, Double maxArea,
                                                             String factAndFactory) {
        OwnerPropertyCriteriaRequest ownerPropertyCriteriaRequest = new OwnerPropertyCriteriaRequest();

        ownerPropertyCriteriaRequest.setDealType (dealType);
        ownerPropertyCriteriaRequest.setMinPrice(minPrice);
        ownerPropertyCriteriaRequest.setMaxPrice(maxPrice);
        ownerPropertyCriteriaRequest.setHomeType(homeType);
        ownerPropertyCriteriaRequest.setNumBath(numBath);
        ownerPropertyCriteriaRequest.setNumBed(numBed);
        ownerPropertyCriteriaRequest.setFactAndFactory(factAndFactory);
        ownerPropertyCriteriaRequest.setMinArea(minArea);
        ownerPropertyCriteriaRequest.setMaxArea(maxArea);
        ownerPropertyCriteriaRequest.setOwnerId(ownerId);
        return listMapper.mapList(ownerPropertySearchDao.findAllByCriteria(ownerPropertyCriteriaRequest),
                PropertyDto.class) ;
    }
}
