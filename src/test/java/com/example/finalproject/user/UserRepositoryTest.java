package com.example.finalproject.user;

import com.example.finalproject.domain.photo.Photo;
import com.example.finalproject.domain.user.User;
import com.example.finalproject.domain.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

//    @Test
//    public void findUsersByStatus_test(){
//        // given
//
//        // when
//        List<User> creatorPage = userRepository.findUsersByStatus();
//        // then
//        System.out.println("찾았다 : "+ creatorPage.get(0));
//    }



}
