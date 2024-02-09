package com.baseProject.cafofo.repo;

import com.baseProject.cafofo.entity.DealType;
import com.baseProject.cafofo.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PropertyRepo extends JpaRepository<Property, Long> {
    @Query("Select size(p.offers) from Property p join p.offers o where p.id = :propertyId")
    public Integer findPropertyByOffers(Long propertyId);
    @Query("Select p from Property p join p.owner o where o.id= :ownerId and p.id = :propId")
    public Property findPropertyByOwnerEquals(Long ownerId, Long propId);

    @Query("Select p from Property p join p.owner o where o.id= :ownerId")
    public List<Property> findAllPropertyByOwner(Long ownerId);

    @Query("Select p from Property p join p.owner o where o.id= :ownerId and p.dealType = :dealType")
    public List<Property> findAllPropertyByOwnerWithDealType(Long ownerId, DealType dealType);

    public List<Property> findPropertiesByDealType(DealType dealType);
    @Query("select p from Property p where p.approvalStatus = true ")
    public List<Property> findPropertiesByApprovalStatus();

    Collection<Property> findByApprovalStatus(boolean b);
}
