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

    // 코디 최신순 정렬 + 사진까지
    @Query("select c from Codi c join fetch c.photos p where p.isMainPhoto = true order by c.createdAt desc")
    List<Codi> findAllByOrderByDateDescWithPhoto();

    @Query("select c from Codi c join fetch c.photos p")
    List<Codi> findByAllCodi();

    // JPQL 쿼리 예시 (필요한 경우 추가)
    @Query("SELECT c FROM Codi c JOIN FETCH c.photos p WHERE p.isMainPhoto = true and c.description LIKE %:keyword%")
    List<Codi> findByDescriptionContaining(@Param("keyword") String keyword);
//    @Query("SELECT DISTINCT c FROM Codi c " +
//            "LEFT JOIN fetch c.photos p " +
//            "WHERE (p IS NULL OR p.isMainPhoto = true) AND c.description LIKE %:keyword%")

}
