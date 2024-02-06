package com.baseProject.cafofo.repo;

import com.baseProject.cafofo.entity.Offer;
import com.baseProject.cafofo.entity.Owner;
import com.baseProject.cafofo.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;

@Repository
public interface OwnerRepo extends JpaRepository<Owner,Long> {
    @Query("Select o.properties From Owner o join o.properties p join p.offers offer where offer.offerStatus = 'PENDING' and o.User.id = :userId")
    Collection<Property> getOwnerPropertiesByPlaced(Long userId);

}
