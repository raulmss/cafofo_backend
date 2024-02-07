package com.baseProject.cafofo.Repository;

import com.baseProject.cafofo.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
}
