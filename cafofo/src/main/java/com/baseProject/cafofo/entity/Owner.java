package com.baseProject.cafofo.entity;

import com.baseProject.cafofo.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JsonManagedReference
    private User User;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    @JsonManagedReference
    private Collection<Property> properties;

}
