package com.baseProject.cafofo.service;

import com.baseProject.cafofo.dto.PropertyDto;
import com.baseProject.cafofo.entity.DealType;
import com.baseProject.cafofo.entity.Property;
import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.List;

public interface PropertyService {
    public Collection<PropertyDto> findAllPropertyByOwner(Long ownerid);

    public PropertyDto findPropertyDetailByOwner(Long ownerId,Long propertyId);

    public void save(PropertyDto p);

    public void update (Long propertyId,PropertyDto p);

    public void delete(Long propertyId);

    List<Property> searchMinMaxProperty(String dealType, Double minPrice, Double maxPrice, Double numBed, Double numBath, String homeType, Double minArea, Double maxArea, String factAndFactory);
    Collection<PropertyDto> findPropertyByCustomer(Long cid);

    Collection<PropertyDto> findAllPropertyByOwnerWithDealType(Long ownerid, DealType dealtype);
    Collection<PropertyDto> findPropertyByGuest();

    PropertyDto findPropertyDetail(Long propId, Long custId);

    Collection<PropertyDto> findPropertyByCustomerByDealType(Long custId, DealType dealtype);

    Collection<PropertyDto> findPropertyByGuestWithDealType(DealType dealType);

    List<Property> searchAddressGuest(String country, String state, String city, String street, String homenumber, String zip);

    Collection<PropertyDto>  searchAddressCustomer(Long customerId, String country, String state, String city, String street, String homenumber, String zip);

    List<Property> searchAddressOwner(Long ownerId, String country, String state, String city, String street, String homenumber, String zip);

}

