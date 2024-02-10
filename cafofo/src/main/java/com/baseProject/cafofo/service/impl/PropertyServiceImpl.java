package com.baseProject.cafofo.service.impl;


import com.baseProject.cafofo.dto.*;
import com.baseProject.cafofo.entity.*;
import com.baseProject.cafofo.exceptions.CafofoApplicationException;
import com.baseProject.cafofo.helper.ListMapper;
import com.baseProject.cafofo.repo.*;
import com.baseProject.cafofo.service.PropertyService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    PropertyAddressGuestDao propertyAddressGuestDao;

    @Autowired
    PropertyAddressOwnerDao propertyAddressOwnerDao;

    public Collection<PropertyDto> findAllGuest(){
        Collection<Property> properties = propertyRepo.findAll();
        Collection<PropertyDto> propertyDtos = new ArrayList<>();
//        ModelMapper modelMapper = new ModelMapper();
        for (Property property : properties) {
            PropertyDto propertyDto = modelMapper.map(property, PropertyDto.class);
            if(property.getApprovalStatus() != false){
                propertyDto.setPropertyStatus(property.getPropertyStatus());
            }
            else {
                propertyDto.setPropertyStatus(PropertyStatus.INITIAL);
            }
            propertyDto.setIsFavorite(false);
            propertyDtos.add(propertyDto);
        }
        return propertyDtos;
    }
    @Transactional
    public Collection<PropertyDto> findAllPropertyByOwner(Long ownerid){
        Collection<Property> properties = propertyRepo.findAllPropertyByOwner(ownerid);
        if(properties==null || properties.equals(null)){
            throw new CafofoApplicationException("There is no properties for this owner");
        }
        Collection<PropertyDto> propertyDtos = new ArrayList<>();
//        ModelMapper modelMapper = new ModelMapper();

        for (Property property : properties) {
            PropertyDto propertyDto = modelMapper.map(property, PropertyDto.class);
            System.out.println("Model mappert lists ");
            if(property.getApprovalStatus() != false){
                propertyDto.setPropertyStatus(property.getPropertyStatus());
            }
            else {
                propertyDto.setPropertyStatus(PropertyStatus.INITIAL);
            }
            propertyDto.setIsFavorite(false);
            propertyDtos.add(propertyDto);
        }
        return propertyDtos;
    }
@Transactional
@Override
    public Collection<PropertyDto> findAllPropertyByOwnerWithDealType(Long ownerid, DealType dealtype){
    System.out.println("<<service>>"+dealtype);
        Collection<Property> properties = propertyRepo.findAllPropertyByOwnerWithDealType(ownerid,dealtype);
        if(properties==null || properties.equals(null)){
        throw new CafofoApplicationException("There is no properties for this owner");
            }
        Collection<PropertyDto> propertyDtos = new ArrayList<>();
//        ModelMapper modelMapper = new ModelMapper();
        for (Property property : properties) {
            PropertyDto propertyDto = modelMapper.map(property, PropertyDto.class);
            if(property.getApprovalStatus() != false){
                propertyDto.setPropertyStatus(property.getPropertyStatus());
            }
            else {
                propertyDto.setPropertyStatus(PropertyStatus.INITIAL);
            }
            propertyDto.setIsFavorite(false);
            propertyDtos.add(propertyDto);
        }
        return propertyDtos;
    }


    public PropertyDto findPropertyDetailByOwner(Long ownerId,Long propertyId){
//        ModelMapper modelMapper = new ModelMapper();
        Property property = propertyRepo.findPropertyByOwnerEquals(ownerId,propertyId);
        if(property==null || property.equals(null)){
            throw new CafofoApplicationException("There is no properties for this owner");
        }
        PropertyDto propertyDto = modelMapper.map(property,PropertyDto.class);

        if(property.getApprovalStatus() == false){
            propertyDto.setPropertyStatus(PropertyStatus.INITIAL);
        }
        else {
            propertyDto.setPropertyStatus(property.getPropertyStatus());
        }
        propertyDto.setIsFavorite(false);
        return propertyDto;
    }
@Transactional
    public void save(PropertyDto p){
        Collection<ImageDto> imageList = new ArrayList<>();
        for(ImageDto i: p.getImage()){
            i.setPath("C:/PropertyPhoto/"+i.getPath());
            imageList.add(i);

        }
        p.setImage(imageList);
        p.setApprovalStatus(false);

//        ModelMapper modelMapper = new ModelMapper();
        Property property = modelMapper.map(p,Property.class);
        propertyRepo.save(property);
    }

    public void update (Long propertyId,PropertyDto p){
//        ModelMapper modelMapper = new ModelMapper();
        Property property =propertyRepo.findById(propertyId)
                .orElseThrow(()->new CafofoApplicationException("There is no property to update"));
        if(property!= null){
            for(PropImage i: property.getImage()){
                propImageRepo.delete(i);
            }
            Collection<ImageDto> imageList = new ArrayList<>();
            for(ImageDto i: p.getImage()){
                i.setPath("C:/PropertyPhoto/"+i.getPath());
                imageList.add(i);

            }
            p.setImage(imageList);

            Property propertyRequest = modelMapper.map(p,Property.class);
            propertyRepo.save(propertyRequest);
        }

    }
    public void delete(Long propertyId){
        Property p =propertyRepo.findById(propertyId)
                .orElseThrow(()->new CafofoApplicationException("There is no property to delete"));
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
    public Collection<PropertyDto> findPropertyByGuest() {
        Collection<Property> properties = propertyRepo.findPropertiesByApprovalStatus();
        Collection<PropertyDto> propertyDtos = new ArrayList<>();
//        ModelMapper modelMapper = new ModelMapper();
        for (Property property : properties) {
            PropertyDto propertyDto = modelMapper.map(property, PropertyDto.class);
            if(property.getApprovalStatus() != false){
                propertyDto.setPropertyStatus(property.getPropertyStatus());
            }
            else {
                propertyDto.setPropertyStatus(PropertyStatus.INITIAL);
            }
            propertyDto.setIsFavorite(false);

            propertyDtos.add(propertyDto);
        }
        return propertyDtos;
    }
    @Override
    public Collection<PropertyDto> findPropertyByGuestWithDealType(DealType dealType) {
        Collection<Property> properties = propertyRepo.findPropertiesByDealType(dealType);
        Collection<PropertyDto> propertyDtos = new ArrayList<>();
//        ModelMapper modelMapper = new ModelMapper();
        for (Property property : properties) {
            PropertyDto propertyDto = modelMapper.map(property, PropertyDto.class);
            if(property.getApprovalStatus() != false){
                propertyDto.setPropertyStatus(property.getPropertyStatus());
            }
            else {
                propertyDto.setPropertyStatus(PropertyStatus.INITIAL);
            }
            propertyDto.setIsFavorite(false);
            propertyDtos.add(propertyDto);
        }
        return propertyDtos;
    }



    @Override
    public PropertyDto findPropertyDetail(Long propId, Long cusId) {
//        ModelMapper modelMapper = new ModelMapper();
        Property property= propertyRepo.findById(propId).get();
        PropertyDto propertyDto =modelMapper.map(property,PropertyDto.class);
        Customer c = customerRepo.findById(cusId).get();

        if(property != null) {
            if (property.getApprovalStatus() != false) {
                propertyDto.setPropertyStatus(property.getPropertyStatus());
            } else {
                propertyDto.setPropertyStatus(PropertyStatus.INITIAL);
            }
            long cus = c.getFavoriteProperties().stream().filter(f -> f.getId().equals(property.getId())).count();
            if (cus > 0) {
                propertyDto.setIsFavorite(true);
            } else {
                propertyDto.setIsFavorite(false);
            }
        }

        return propertyDto;
    }

    @Override
    public Collection<PropertyDto> findPropertyByCustomer(Long custId) {
//        ModelMapper modelMapper = new ModelMapper();
        List<Property> properties = propertyRepo.findAll();
        Collection<PropertyDto> propertyDtos = new ArrayList<>();
        Customer c = customerRepo.findById(custId).get();
        System.out.println("<<>>"+c.getId());
        if(c != null){
            for (Property property : properties) {
                PropertyDto propertyDto = modelMapper.map(property, PropertyDto.class);
                if(property.getApprovalStatus() != false){
                    propertyDto.setPropertyStatus(property.getPropertyStatus());
                }
                else {
                    propertyDto.setPropertyStatus(PropertyStatus.INITIAL);
                }
                long cus = c.getFavoriteProperties().stream().filter(f -> f.getId().equals(property.getId())).count();
                if(cus > 0){
                    propertyDto.setIsFavorite(true);
                }else{
                    propertyDto.setIsFavorite(false);
                }
                propertyDtos.add(propertyDto);
            }
        }
        return propertyDtos;
    }

    public Collection<PropertyDto> findPropertyByCustomerByDealType(Long custId, DealType dealtype) {
//        ModelMapper modelMapper = new ModelMapper();
        List<Property> properties = propertyRepo.findPropertiesByDealType(dealtype);
        Collection<PropertyDto> propertyDtos = new ArrayList<>();
        Customer c = customerRepo.findById(custId).get();
        if(c != null){
            for (Property property : properties) {
                PropertyDto propertyDto = modelMapper.map(property, PropertyDto.class);
                if(property.getApprovalStatus() != false){
                    propertyDto.setPropertyStatus(property.getPropertyStatus());
                }
                else {
                    propertyDto.setPropertyStatus(PropertyStatus.INITIAL);
                }
                long cus = c.getFavoriteProperties().stream().filter(f -> f.getId().equals(property.getId())).count();
                if(cus > 0){
                    propertyDto.setIsFavorite(true);
                }else{
                    propertyDto.setIsFavorite(false);
                }
                propertyDtos.add(propertyDto);
            }
        }
        return propertyDtos;
    }

    @Override
    public List<Property> searchAddressGuest(String country, String state, String city, String street, String homenumber, String zip) {
        AddressPropertyCriterialRequest propertyCriteriaRequest = new AddressPropertyCriterialRequest();
        propertyCriteriaRequest.setCity(city);
        propertyCriteriaRequest.setZip(zip);
        propertyCriteriaRequest.setStreet(street);
        propertyCriteriaRequest.setCountry(country);
        propertyCriteriaRequest.setNumber(homenumber);
        propertyCriteriaRequest.setState(state);

        return propertyAddressGuestDao.findAllByCriteria(propertyCriteriaRequest);
    }

    @Override
    public Collection<PropertyDto> searchAddressCustomer(Long customerId, String country, String state, String city, String street, String homenumber, String zip) {
        AddressPropertyCriterialRequest propertyCriteriaRequest = new AddressPropertyCriterialRequest();
        propertyCriteriaRequest.setCity(city);
        propertyCriteriaRequest.setZip(zip);
        propertyCriteriaRequest.setStreet(street);
        propertyCriteriaRequest.setCountry(country);
        propertyCriteriaRequest.setNumber(homenumber);
        propertyCriteriaRequest.setState(state);
        List<Property> properties = propertyAddressGuestDao.findAllByCriteria(propertyCriteriaRequest);
        List<PropertyDto> propertyDtos = new ArrayList<>();
        if(properties.size() > 0){
            for(Property p: properties){
                Customer c = customerRepo.findById(customerId).get();
                PropertyDto propertyDto = modelMapper.map(p, PropertyDto.class);
                long cus = c.getFavoriteProperties().stream().filter(f -> f.getId().equals(p.getId())).count();
                if(cus > 0){
                    propertyDto.setIsFavorite(true);
                }else{
                    propertyDto.setIsFavorite(false);
                }
                propertyDtos.add(propertyDto);
            }
        }
        return propertyDtos;
    }

    @Override
    public List<Property> searchAddressOwner(Long ownerId, String country, String state, String city, String street, String homenumber, String zip) {
        AddressOwnerPropertyCriterialRequest propertyCriteriaRequest = new AddressOwnerPropertyCriterialRequest();
        propertyCriteriaRequest.setCity(city);
        propertyCriteriaRequest.setZip(zip);
        propertyCriteriaRequest.setStreet(street);
        propertyCriteriaRequest.setCountry(country);
        propertyCriteriaRequest.setNumber(homenumber);
        propertyCriteriaRequest.setState(state);
        propertyCriteriaRequest.setOwnerid(ownerId);

        return propertyAddressOwnerDao.findAllByCriteria(propertyCriteriaRequest);
    }


}
