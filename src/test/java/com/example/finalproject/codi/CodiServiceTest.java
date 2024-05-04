package com.example.finalproject.codi;

import com.example.finalproject.domain.codi.CodiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(CodiService.class)
@DataJpaTest
public class CodiServiceTest {
    @Autowired
    private CodiService codiService;

    @Test
    public void codiPage_test() {
        // given
        Integer codiId = 1;
        Integer userId = 2;
        // when
        codiService.codiPage(codiId, userId);
        // then

    }

}
