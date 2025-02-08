package com.baseProject.cafofo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "offer")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private OfferStatus offerStatus;

    private double offerPrice;

    private LocalDateTime offerDate;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "property_id")
    private Property property;

    private boolean cancel_flag=false;

}