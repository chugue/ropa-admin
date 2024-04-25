package com.example.finalproject.domain.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

    //아이디 중복체크
    Optional<Admin> findByEmail(@Param("email") String email);

    //로그인
    Optional<Admin> findByEmailAndPassword(@Param("email") String email, @Param("password") String password);


}
