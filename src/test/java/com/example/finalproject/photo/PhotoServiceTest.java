package com.example.finalproject.photo;


import com.example.finalproject.domain.photo.PhotoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(PhotoService.class)
@DataJpaTest
public class PhotoServiceTest {
    @Autowired
    private PhotoService photoService;


    @Test
    public void findAllcodiAllItems_test(){
        // given

        // when

        // then

    }
}
