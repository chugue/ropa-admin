package com.example.finalproject.domain.items;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface ItemsRepository extends JpaRepository<Items, Integer> {

    //브랜드의 아이템 목록
    @Query("SELECT i FROM Items i JOIN FETCH i.category WHERE i.admin.id = :adminId")
    List<Items> findItemsByAdminId(@Param("adminId") int adminId);

    // 브랜드 아이템 상세보기
    @Query("SELECT i FROM Items i JOIN FETCH i.category c WHERE i.admin.id = :adminId AND i.id = :itemId")
    Optional<Items> findItemsByAdminIdAndItemId(@Param("adminId") int adminId, @Param("itemId") int itemId);

}