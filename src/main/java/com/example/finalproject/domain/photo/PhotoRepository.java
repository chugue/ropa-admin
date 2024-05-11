package com.example.finalproject.domain.photo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
    @Query("SELECT ci.items.photos FROM CodiItems ci WHERE ci.codi.id IN :codiIds")
    List<Photo> findPhotosByCodiIds(@Param("codiIds") List<Integer> codiIds);

    // 아이템 아이디들로 사진조회하는 쿼리
    @Query("select p from Photo p join fetch p.items i join fetch i.category c where p.isMainPhoto = true and p.items.id in :itemsIds")
    List<Photo> findByItemsIds(@Param("itemsIds") List<Integer> itemsIds);

    // 코디 아이디로 코디 메인 사진 조회 + 좋아요 정보
    @Query("select p from Photo p where p.codi.id = :codiId")
    List<Photo> findByCodiId(@Param("codiId") Integer codiId);

    // 사용자의 ID로 코디를 찾아서 그 코디의 사진 찾기
    @Query("select p from Photo p where p.isMainPhoto = true and p.codi.id in (select c.id from Codi c where c.user.id = :userId)")
    List<Photo> findByUserIdWithCodiesAndPhoto(@Param("userId") Integer userId);

    // 사용자의 id리스트로 대표 사진 찾기
    @Query("select p from Photo p where p.user.id in :userIds")
    List<Photo> findByUserId(@Param("userIds") List<Integer> userIds);

    // 코디 아이디 리스트로 사진 조회
    @Query("select p from Photo p where p.isMainPhoto = true and p.codi.id in :codiIds")
    List<Photo> findByCodiIds(@Param("codiIds") List<Integer> codiIds);

    // 아이템 아이디로 모든 사진 조회
    @Query("select p from Photo p where p.items.id = :itemsId")
    List<Photo> findAllByItemsId(@Param("itemsId")Integer itemsId);
}
