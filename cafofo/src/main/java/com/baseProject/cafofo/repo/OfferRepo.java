package com.baseProject.cafofo.repo;

import com.baseProject.cafofo.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepo extends JpaRepository<Offer,Long> {

}
