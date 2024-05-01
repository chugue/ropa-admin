package com.example.finalproject.photo;


import com.example.finalproject.domain.photo.Photo;
import com.example.finalproject.domain.photo.PhotoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@DataJpaTest
public class PhotoRepositoryTest {
    @Autowired
    private PhotoRepository photoRepository;


    @Test
    public void findByItemsIds_test(){
        // given
        List<Integer> list = new ArrayList<>(Arrays.asList(2, 7, 3, 4, 5));
        // when
        List<Photo> respList = photoRepository.findByItemsIds(list);
        // then
        respList.forEach(System.out::println);
    }
}
