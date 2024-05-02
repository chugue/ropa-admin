
package com.example.finalproject.domain.user;

import com.example.finalproject.domain.photo.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.email = :email and u.password = :password")
    Optional<User> findByEmailAndPassword(@Param("email") String email, @Param("password") String password);


}
