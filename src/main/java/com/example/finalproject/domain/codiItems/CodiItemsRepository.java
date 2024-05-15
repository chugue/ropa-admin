package com.example.finalproject.domain.codiItems;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CodiItemsRepository extends JpaRepository<CodiItems, Integer> {

    @Query("SELECT ci FROM CodiItems ci JOIN FETCH ci.items WHERE ci.codi.id IN :codiIds")
    List<CodiItems> findCodiItemsByCodiIdIn(@Param("codiIds") List<Integer> codiIds);

    @Query("SELECT ci FROM CodiItems ci LEFT JOIN FETCH ci.codi c LEFT JOIN FETCH c.user u WHERE u.id = :userId")
    List<CodiItems> findCodiItemsByUserId(@Param("userId") Integer userId);

    // codiItems의 양방향 테이블로 코디랑 연결된 아이템 가져오기
    @Query("select ci from CodiItems ci join fetch ci.items i join fetch ci.codi c where ci.codi.id = :codiId")
    List<CodiItems> findByCodiWithItems(@Param("codiId") Integer codiId);

    // 특정 아이템 ID를 가진 코디 아이템이 존재하는지 여부를 확인하는 메서드
    @Query("SELECT CASE WHEN COUNT(ci) > 0 THEN true ELSE false END FROM CodiItems ci WHERE ci.items.id = :itemId")
    Boolean existsByItemId(@Param("itemId") Integer itemId);

    // 특정 아이템 ID를 가진 코디 아이템을 조회하는 메서드
    @Query("SELECT ci FROM CodiItems ci WHERE ci.items.id = :itemId")
    CodiItems findByItemId(@Param("itemId") Integer itemId);

}
