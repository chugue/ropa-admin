package com.example.finalproject.domain.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    @Query("select a from Admin a join fetch a.photo")
    List<Admin> findAdminByPhoto();

    Optional<Admin> findByEmail(@Param("email") String email);

    Optional<Admin> findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    @Query("SELECT a FROM Admin a ")
    Optional<Admin> findFirst();
}
