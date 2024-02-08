package com.baseProject.cafofo.repo;

import com.baseProject.cafofo.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OfferRepo extends JpaRepository<Offer,Long> {
    @Query("select o from Offer o join o.property p join o.customer c where c.id=:customerId and p.id=:propertyId and o.cancel_flag=false ")
    Optional<Offer> searchOffer(Long customerId, Long propertyId);

    @Query("select o from Offer o join o.property p where p.id=:propertiesId and o.id=:offerId")
    Optional<Offer> findById(Long propertiesId, Long offerId);
}
