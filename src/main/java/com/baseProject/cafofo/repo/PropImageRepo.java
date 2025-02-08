package com.baseProject.cafofo.repo;

import com.baseProject.cafofo.entity.PropImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropImageRepo extends JpaRepository<PropImage,Long> {
}
