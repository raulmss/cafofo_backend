package com.baseProject.cafofo.entity;

import com.baseProject.cafofo.user.User;
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
    private User User;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Collection<Property> properties;

}
