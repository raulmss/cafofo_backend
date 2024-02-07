package com.baseProject.cafofo.service;

import com.baseProject.cafofo.dto.PropertyDto;
import com.baseProject.cafofo.entity.Property;
import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.List;

public interface PropertyService {
    public Collection<PropertyDto> findAll(Long ownerid);

    public PropertyDto findAllById(Long ownerId,Long propertyId);

    public void save(PropertyDto p);

    public void update (Long propertyId,PropertyDto p);

    public void delete(Long propertyId);

    Collection<PropertyDto> searchMinMaxProperty(String dealType, Double minPrice, Double maxPrice, Double numBed, Double numBath, String homeType, Double minArea, Double maxArea, String factAndFactory);


}
