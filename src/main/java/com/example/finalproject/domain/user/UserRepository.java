package com.example.finalproject.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {



    //크리에이터의 정보 불러오기
    @Query("select u from User u join fetch u.photo where u.id = :userId and u.blueChecked = true ")
    Optional<User> findUsersByBlueCheckedAndPhoto(@Param("userId") Integer userId);



    @Query("select u from User u where u.email = :email and u.password = :password")
    Optional<User> findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.photo WHERE u.id = :userId")
    Optional<User> findByUserIdWithPhoto(@Param("userId") Integer userId);

    @Query("SELECT c.user FROM CodiItems ci JOIN ci.items i JOIN ci.codi c WHERE i.id = :itemId")
    List<Integer> findCreatorByItemId(@Param("itemId") Integer itemId);

    // 유저 실명 검색
    @Query("SELECT u FROM User u where u.myName like %:keyword%")
    List<User> findByMyName(@Param("keyword") String keyword);

    // 유저 닉네임 검색
    @Query("SELECT u FROM User u where u.nickName like %:keyword%")
    List<User> findByNickName(@Param("keyword") String keyword);

    // 유저 이메일 검색
    @Query("SELECT u FROM User u where u.email like %:keyword%")
    List<User> findByEmail(@Param("keyword") String keyword);
}
