package com.example.finalproject.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT " +
            "NEW com.example.finalproject.domain.user.UserResponse$CreatorViewDTO(ci.codi.user.id, " +
            "NEW com.example.finalproject.domain.user.UserResponse.CreatorViewDTO.UserDTO(ci.codi.user.id, ci.codi.user.blueChecked, ci.codi.user.photo.name, ci.codi.user.photo.path, ci.codi.user.nickName, ci.codi.user.height, ci.codi.user.weight, ci.codi.user.job, ci.codi.user.introMsg), " +
            "NEW com.example.finalproject.domain.user.UserResponse.CreatorViewDTO.CodiListDTO(ci.codi.id, ci.codi.photos.first().id, ci.codi.photos.first().name, ci.codi.photos.first().path, ci.codi.photos.first().sort), " +
            "NEW com.example.finalproject.domain.user.UserResponse.CreatorViewDTO.ItemListDTO(ci.items.id, ci.items.name, ci.items.description, ci.items.price, ci.items.photos.first().name, ci.items.photos.first().path, ci.items.photos.first().sort)) " +
            "FROM CodiItems ci " +
            "WHERE ci.codi.user.id = :userId")
    List<UserResponse.CreatorViewDTO> findByUserWithCodiLIstItemsList(@Param("userId") Integer userId);

    //크리이터인지 확인
    @Query("SELECT u FROM User u WHERE u.blueChecked = true")
    List<User> findCreators();

    @Query("select u from User u where u.email = :email and u.password = :password")
    Optional<User> findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.photo WHERE u.id = :userId")
    Optional<User> findByUserIdWithPhoto(@Param("userId") Integer userId);

}
