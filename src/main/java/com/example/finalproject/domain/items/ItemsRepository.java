package com.example.finalproject.domain.items;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface ItemsRepository extends JpaRepository<Items, Integer> {
    //브랜드 기준으로 브랜드 정보와 본인이 올린 아이템들에 대한 정보를 가져오는 쿼리
    @Query("select i from Items i join fetch i.photos join fetch i.admin where i.admin.id = :adminId")
    List<Items> findByAdminItemsAndPhotos(@Param("adminId")int adminId);
    //아이템 정보와 해당 아이템을 올린 브랜드 정보 가져오기
    @Query("select i from Items i join fetch i.photos join fetch i.admin where i.id = :itemId")
    Items findItemsByAdminAndPhotos(@Param("itemId") int itemId);

    // 여러 코디에 대한 아이템과 포토 정보를 가져오는 쿼리
    @Query("SELECT ci.items FROM CodiItems ci join fetch ci.items.photos WHERE ci.codi.id IN :codiIds")
    List<Items> findItemsByCodiIds(@Param("codiIds") List<Integer> codiIds);

    //브랜드의 아이템 목록
    @Query("SELECT i FROM Items i JOIN FETCH i.category WHERE i.status = true AND i.admin.id = :adminId")
    List<Items> findItemsByAdminId(@Param("adminId") int adminId);

    // 브랜드 아이템 상세보기
    @Query("SELECT i FROM Items i JOIN FETCH i.category c WHERE i.admin.id = :adminId AND i.id = :itemId")
    Optional<Items> findItemsByAdminIdAndItemId(@Param("adminId") int adminId, @Param("itemId") int itemId);

}