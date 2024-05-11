package com.example.finalproject.domain.codi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CodiRepository extends JpaRepository<Codi, Integer> {
    //선택된 크리에이터의 정보와 관련된 코디 목록 가져오기
    @Query("SELECT c FROM Codi c join fetch c.photos WHERE c.user.id = :userId")
    List<Codi> findCodiByUserId(@Param("userId") int userId);


    @Query("SELECT DISTINCT c FROM Codi c JOIN FETCH c.photos WHERE c.user.id = :userId")
    List<Codi> findCodiAndPhotosByUserId(@Param("userId") int userId);

    @Query("select c from Codi c where c.id = :codiId")
    Optional<Codi> findByCodiId(@Param("codiId") Integer codiId);

    @Query("select c from Codi c join fetch c.user u where c.id = :codiId")
    Optional<Codi> findByCodiIdAndUser(@Param("codiId") Integer codiId);

    // 코디 최신순 정렬
    @Query("select c from Codi c order by c.createdAt desc")
    List<Codi> findAllByOrderByDateDesc();
}
