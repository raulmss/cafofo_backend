package com.baseProject.cafofo.repo;

import com.baseProject.cafofo.user.Role;
import com.baseProject.cafofo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface AdminRepo extends JpaRepository<User, Long> {
    @Query("select u from User u where u.role=:userRole")
    Collection<User> findByUser(Role userRole);


}
