package com.baseProject.cafofo.entity;

import com.baseProject.cafofo.entity.Address;
import com.baseProject.cafofo.entity.DealType;
import com.baseProject.cafofo.entity.HomeType;
import com.baseProject.cafofo.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Collection<PropImage> image;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "property")
    private Collection<Offer> offers;

    @ManyToOne
    private Owner owner;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "price")
    private Double price;

    @Column(name = "number_of_bed")
    private Integer numberOfBed;

    @Column(name = "number_of_bathroom")
    private Integer numberOfBathRoom;

    @ElementCollection
    @CollectionTable(name = "fact_and_features", joinColumns = @JoinColumn(name = "property_id"))
    @Column(name = "feature")
    private List<String> factAndFeatures;

    @Enumerated(EnumType.STRING)
    @Column(name = "home_type")
    private HomeType homeType;

    @Enumerated(EnumType.STRING)
    @Column(name = "deal_type")
    private DealType dealType;

    @Column(name = "area")
    private Double area;
}