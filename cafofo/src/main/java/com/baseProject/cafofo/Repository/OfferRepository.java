package com.baseProject.cafofo.Repository;

import com.baseProject.cafofo.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OfferRepository extends JpaRepository<Offer,Long > {

//    @Query(value = "select o from Offer o join o.customer c where c.user.id=:userId")
//    List<Offer> findByUserId(Long userId);


}
