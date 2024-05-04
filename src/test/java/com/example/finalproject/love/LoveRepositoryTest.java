package com.example.finalproject.love;


import com.example.finalproject.domain.love.LoveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class LoveRepositoryTest {
    @Autowired
    private LoveRepository loveRepository;




}
