package com.example.finalproject.domain.items;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ItemsRepository extends JpaRepository<Items, Integer> {

    //브랜드의 상품 목록
    @Query("SELECT i FROM Items i JOIN FETCH i.category WHERE i.admin.id = :adminId")
    List<Items> findItemsByAdminId(@Param("adminId") int adminId);



}