package com.example.finalproject.domain.love;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoveRepository extends JpaRepository <Love, Integer> {


    @Query("select l.codi , count(l) as loveCount from Love l where l.isLoved = true group by l.codi order by loveCount desc")
    List<Object[]> findAllSortByLoveCount();
}
