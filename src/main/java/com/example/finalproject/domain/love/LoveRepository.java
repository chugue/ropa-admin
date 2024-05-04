package com.example.finalproject.domain.love;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoveRepository extends JpaRepository <Love, Integer> {


    // 좋아요를 받은 순으로 코디를 정렬해서 사용자 id기준으로 그룹화후 총 좋아요순으로 나열
    @Query("SELECT new com.example.finalproject.domain.love.LoveRequest$UserLoveCount(c.user.id, COUNT(l)) " +
            "FROM Codi c JOIN c.loves l WHERE l.isLoved = true " +
            "GROUP BY c.user.id ORDER BY count(l) DESC")
    List<LoveRequest.UserLoveCount> findUserIdsSortedByLoveCount();

    // 좋아요를 받은 순으로 코디를 정렬
    @Query("select new com.example.finalproject.domain.love.LoveRequest$CodiLoveCount(c.id, count(l))" +
            " from Codi c join c.loves l where l.isLoved = true group by c.id order by count(l) desc")
    List<LoveRequest.CodiLoveCount> findCodiIdsSortedByLoveCount();
}

