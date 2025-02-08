package com.baseProject.cafofo.service;


import com.baseProject.cafofo.dto.OfferDto;
import com.baseProject.cafofo.dto.OfferRequest;
import com.baseProject.cafofo.dto.PropertyDto;
import com.baseProject.cafofo.entity.Offer;
import com.baseProject.cafofo.entity.Owner;
import com.baseProject.cafofo.entity.Property;
import com.baseProject.cafofo.helper.ListMapper;
import com.baseProject.cafofo.repo.OwnerRepo;
import com.baseProject.cafofo.service.impl.OwnerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

public interface OwnerService{

    public Collection<PropertyDto> getOwnerPropertiesByPlaced(Long ownerId);


    List<Property> searchOwnerMinMaxProperty(Long ownerId, String dealType, Double minPrice, Double maxPrice,
                                             Double numBed, Double numBath, String homeType,
                                             Double minArea, Double maxArea, String factAndFactory);
                                             
                                             
    Collection<OfferDto> findOffersByPropertiesId(Long ownerId, Long propertiesId);

    void approveOffer(Long ownerId, Long propertiesId, Long offerId);

    void rejectOffer(Long ownerId, Long propertiesId, Long offerId);

    Owner findById (Long ownerId);

    List<Offer> findOfferByOwnerId(Long ownerId);

}
