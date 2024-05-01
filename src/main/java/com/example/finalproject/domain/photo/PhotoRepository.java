package com.example.finalproject.domain.photo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhotoRepository extends JpaRepository <Photo, Integer> {

    @Query("SELECT p FROM Photo p JOIN fetch p.user u where u.mileage >= 100 ORDER BY u.mileage DESC")
    List<Photo> findByMileageWithPhoto();

    // 아이템 아이디들로 사진조회하는 쿼리
    @Query("select p from Photo p where p.items.id in :itemsIds")
    List<Photo> findByItemsIds(@Param("itemsIds") List<Integer> itemsId);
}
