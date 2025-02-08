package com.baseProject.cafofo.repo;

import com.baseProject.cafofo.entity.Customer;
import com.baseProject.cafofo.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    @Query(value = "select o  from Offer o where o.customer.id=:userId and o.id=:offerId")
    Offer checkOffer(Long userId, Long offerId);

    @Query(value = "select o  from Offer o where o.customer.id=:userId and o.cancel_flag= false")
    List<Offer> findOffersByCustomerId(@Param("userId") Long userId);

    @Modifying
    @Query("UPDATE Offer o SET o.cancel_flag = true WHERE o.id = :offerId AND o.customer.id = :userId")
    void cancelOffer(Long offerId, Long userId);
    @Modifying
    @Query("UPDATE Offer o SET o.offerPrice = :price WHERE o.id = :offerId AND o.customer.id = :userId")
    void maintainOfferByPrice(double price, Long offerId, Long userId);

    @Query("select o.property.owner.email from Offer o where o.id=:offerId and o.customer.id=:userId")
    String getOwnerEmail(Long offerId, Long userId);


}
