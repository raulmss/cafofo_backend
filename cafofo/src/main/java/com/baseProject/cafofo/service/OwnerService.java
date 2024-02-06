package com.baseProject.cafofo.service;

import com.baseProject.cafofo.dto.PropertyDto;
import com.baseProject.cafofo.help.ListMapper;
import com.baseProject.cafofo.repo.OwnerRepo;
import com.baseProject.cafofo.service.impl.OwnerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


public interface OwnerService{

    public Collection<PropertyDto> getOwnerPropertiesByPlaced(Long ownerId);


    Collection<PropertyDto> searchOwnerMinMaxProperty(Long ownerId, String dealType, Double minPrice, Double maxPrice,
                                                      Double numBed, Double numBath, String homeType,
                                                      Double minArea, Double maxArea, String factAndFactory);
}
