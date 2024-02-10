package com.baseProject.cafofo.repo;

import com.baseProject.cafofo.entity.Offer;
import com.baseProject.cafofo.entity.Owner;
import com.baseProject.cafofo.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public interface OwnerRepo extends JpaRepository<Owner,Long> {
    @Query("Select p From Property p join p.offers offer where offer.offerStatus = 'PENDING' and p.owner.id = :userId")
    Collection<Property> getOwnerPropertiesByPlaced(Long userId);

    @Query("select po from Owner o join o.properties p join p.offers po where o.id=:ownerId and p.id=:propertiesId")
    List<Offer> findOffersByPropertiesId(Long ownerId, Long propertiesId);

    @Query("select u.email from User u where u.id=:userId")
    String getCustomerEmail(Long userId);

    @Query("select o from Offer o where o.id=:offerId and o.customer.id=:userId")
    Offer checkOffer(Long userId, Long offerId);

    @Query("select o from Offer o join o.property p where p.owner.id=:ownerId")
    List<Offer> findOfferByOwnerId(Long ownerId);
}
