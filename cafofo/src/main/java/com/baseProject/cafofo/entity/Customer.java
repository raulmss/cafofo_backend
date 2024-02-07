package com.baseProject.cafofo.entity;

import com.baseProject.cafofo.user.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    @JsonManagedReference
    private Collection<Offer> offers;

    @ManyToMany
    @JoinTable(
            name = "favorite_properties",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "property_id")
    )
    private Collection<Property> favoriteProperties = new ArrayList<>();

}
