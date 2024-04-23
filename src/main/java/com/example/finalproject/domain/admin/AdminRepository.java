package com.example.finalproject.domain.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByEmail(@Param("email") String email);

    Optional<Admin> findByEmailAndPassword(@Param("email") String email, @Param("password") String password);


}
