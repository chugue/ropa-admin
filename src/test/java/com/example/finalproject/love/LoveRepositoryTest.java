package com.example.finalproject.love;


import com.example.finalproject.domain.love.LoveRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

@DataJpaTest
public class LoveRepositoryTest {
    @Autowired
    private LoveRepository loveRepository;


    @Test
    public void findAllSortByLoveCount_test(){
        // given

        // when
        List<Object[]> loveCodiList = loveRepository.findAllSortByLoveCount();
        // then
        loveCodiList.forEach(objects -> System.out.println(Arrays.toString(objects)));

    }
}
