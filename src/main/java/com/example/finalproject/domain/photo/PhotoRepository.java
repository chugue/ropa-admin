package com.example.finalproject.domain.photo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhotoRepository extends JpaRepository <Photo, Integer> {

    // 아이템 아이디들로 사진조회하는 쿼리
    @Query("select p from Photo p where p.items.id in :itemsIds")
    List<Photo> findByItemsIds(@Param("itemsIds") List<Integer> itemsIds);

    // 코디 아이디로 코디 메인 사진 조회
    @Query("select p from Photo p where p.codi.id = :codiId")
    List<Photo> findByCodiId(@Param("codiId") Integer codiId);

    @Query("select p from Photo p where p.codi.id in (select c.id from Codi c where c.user.id = :userId )")
    List<Photo> findByUserIdWithCodiesAndPhoto(@Param("userId") Integer userId);
}
