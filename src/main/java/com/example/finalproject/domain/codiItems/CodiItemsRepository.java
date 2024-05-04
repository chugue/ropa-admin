package com.example.finalproject.domain.codiItems;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CodiItemsRepository extends JpaRepository<CodiItems, Integer> {
    //해당크리에이터 뷰에 해당 되는 크리에이터 정보, 코디 정보, 아이템 정보 가져오는 쿼리
    @Query("SELECT ci FROM CodiItems ci LEFT JOIN FETCH ci.codi c LEFT JOIN FETCH c.user u LEFT JOIN FETCH ci.items i LEFT JOIN FETCH u.photo p WHERE u.id = :userId")
    List<CodiItems> findByUserWithCodiLIstItemsList(@Param("userId") Integer userId);

    // codiItems의 양방향 테이블로 코디랑 연결된 아이템 가져오기
    @Query("select ci from CodiItems ci join fetch ci.items i join fetch ci.codi c where ci.codi.id = :codiId")
    List<CodiItems> findByCodiWithItems(@Param("codiId") Integer codiId);
}
