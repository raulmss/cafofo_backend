package com.baseProject.cafofo.entity;

import com.baseProject.cafofo.entity.Address;
import com.baseProject.cafofo.entity.DealType;
import com.baseProject.cafofo.entity.HomeType;
import com.baseProject.cafofo.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "property_name")
    private String propertyName;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    private Collection<PropImage> image = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "property")
    @JsonManagedReference
    private Collection<Offer> offers = new ArrayList<>();

    @ManyToOne
    @JsonBackReference
    private Owner owner;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "price")
    private Double price;

    @Column(name = "number_of_bed")
    private Integer numberOfBed;

    @Column(name = "number_of_bathroom")
    private Integer numberOfBathRoom;


    @Column(name = "feature")
    private String factAndFeatures;

    @Enumerated(EnumType.STRING)
    @Column(name = "home_type")
    private HomeType homeType;

    @Enumerated(EnumType.STRING)
    @Column(name = "deal_type")
    private DealType dealType;

    @Column(name = "area")
    private Double area;


    @Column(name = "approvalstatus")
    private Boolean approvalStatus = false;

    @Transient
    private PropertyStatus propertyStatus;


    public PropertyStatus getPropertyStatus() {
        if (offers == null || offers.isEmpty()) {
            return PropertyStatus.AVAILABLE;
        }

        boolean hasPending = false;

        for (Offer offer : offers) {
            if(offer.getOfferStatus() == OfferStatus.ACCEPTED){
                return PropertyStatus.CONTINGENT;
            }else if(offer.getOfferStatus() == OfferStatus.PENDING){
                hasPending = true;
            }
        }

        return hasPending ? PropertyStatus.PENDING : PropertyStatus.AVAILABLE;
    }

    public void addOffer(Offer offer) {
        offers.add(offer);
    }
}