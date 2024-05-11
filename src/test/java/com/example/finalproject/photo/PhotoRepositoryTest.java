package com.example.finalproject.photo;


import com.example.finalproject.domain.photo.Photo;
import com.example.finalproject.domain.photo.PhotoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class PhotoRepositoryTest {
    @Autowired
    private PhotoRepository photoRepository;

    @Test
    public void findByUserIdWithCodiesAndPhoto_test(){
        // given
        Integer userId = 1;
        // when
        List<Photo> photoList = photoRepository.findByUserIdWithCodiesAndPhoto(userId);
        // then
        photoList.forEach(System.out::println);
    }


    @Test
    public void findByItemsIds_test(){
        // given
        List<Integer> list = List.of(1, 2, 3);
        // when
        List<Photo> respList = photoRepository.findByItemsIds(list);
        // then
        for (int i = 0; i < respList.size(); i++) {
            System.out.println(respList.get(i));
        }
    }
}
