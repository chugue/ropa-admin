package com.example.finalproject.domain.love;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LoveRepository extends JpaRepository <Love, Integer> {


    // 좋아요를 받은 순으로 코디를 정렬해서 사용자 id기준으로 그룹화후 총 좋아요순으로 나열
    @Query("SELECT new com.example.finalproject.domain.love.LoveResponse$UserLoveCount(c.user.id, COUNT(l))" +
            "FROM Codi c JOIN c.loves l WHERE l.isLoved = true " +
            "GROUP BY c.user.id ORDER BY count(l) DESC")
    List<LoveResponse.UserLoveCount> findUserIdsSortedByLoveCount();

    // 좋아요를 받은 순으로 코디를 정렬
    @Query("select new com.example.finalproject.domain.love.LoveResponse$CodiLoveCount(c.id, count(l))" +
            " from Codi c join c.loves l where l.isLoved = true group by c.id order by count(l) desc")
    List<LoveResponse.CodiLoveCount> findCodiIdsSortedByLoveCount();

    // 코디 번호와 사용자번호 그리고 좋아요 상태
    @Query("SELECT l FROM Love l WHERE l.codi.id = :codiId AND l.user.id = :userId")
    Optional<Love> findByCodiIdAndUserLoveStatus(@Param("codiId") Integer codiId, @Param("userId") Integer userId);

    // 코디 번호의 전체 좋아요 갯수
    @Query("SELECT COUNT(a) FROM Love a WHERE a.isLoved = true AND a.codi.id = :codiId")
    Long countTotalLove(@Param("codiId") Integer codiId);

}

