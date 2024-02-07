package com.baseProject.cafofo.repo;

import com.baseProject.cafofo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
}
