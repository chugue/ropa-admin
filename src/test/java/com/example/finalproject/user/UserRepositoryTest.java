package com.example.finalproject.user;


import com.example.finalproject.domain.photo.Photo;
import com.example.finalproject.domain.photo.PhotoRepository;
import com.example.finalproject.domain.user.User;
import com.example.finalproject.domain.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByUserIdWithCodiesAndPhoto_test(){
        // given
        Integer userId = 3;
        // when
        Optional<User> user = userRepository.findUsersByBlueCheckedAndPhoto(userId);
        // then
        System.out.println(user);
    }
}
