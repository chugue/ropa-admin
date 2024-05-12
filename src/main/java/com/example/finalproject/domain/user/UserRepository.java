package com.example.finalproject.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    // 크리에이터 페이지의 필요한 정보만 가져오는 쿼리
//    @Query("SELECT u FROM User u WHERE u.status IN ('승인 대기', '승인')")
//    List<User> findUsersByStatus();

    @Query("select u from User u where u.email = :email and u.password = :password")
    Optional<User> findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.photo WHERE u.id = :userId")
    Optional<User> findByUserIdWithPhoto(@Param("userId") Integer userId);

}
