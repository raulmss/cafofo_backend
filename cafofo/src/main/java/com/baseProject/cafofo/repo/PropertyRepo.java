package com.baseProject.cafofo.repo;

import com.baseProject.cafofo.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepo extends JpaRepository<Property,Long> {
}
