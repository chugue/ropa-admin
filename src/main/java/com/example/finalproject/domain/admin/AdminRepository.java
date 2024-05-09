package com.example.finalproject.domain.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    //관리자 정보를 가져 올 때 사진도 같이 가져오기
    @Query("select a from Admin a join fetch  a.photo where a.id = :adminId")
    Optional<Admin> findByAdminAndPhoto(@Param("adminId") Integer adminId);
    @Query("select a from Admin a join fetch a.photo")
    List<Admin> findAdminByPhoto();

    Optional<Admin> findByEmail(@Param("email") String email);

    Optional<Admin> findByEmailAndPassword(@Param("email") String email, @Param("password") String password);



}
