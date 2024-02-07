package com.baseProject.cafofo.repo;

import com.baseProject.cafofo.entity.Offer;
import com.baseProject.cafofo.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OwnerRepo extends JpaRepository<Owner, Long> {

    @Query("select po from Owner o join o.properties p join p.offers po where o.id=:ownerId and p.id=:propertiesId")
    List<Offer> findOffersByPropertiesId(Long ownerId, Long propertiesId);
}
