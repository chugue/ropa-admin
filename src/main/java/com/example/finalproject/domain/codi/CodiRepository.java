package com.example.finalproject.domain.codi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CodiRepository extends JpaRepository<Codi, Integer> {

    @Query("SELECT DISTINCT c FROM Codi c JOIN FETCH c.photos WHERE c.user.id = :userId")
    List<Codi> findCodiAndPhotosByUserId(@Param("userId") int userId);


}
