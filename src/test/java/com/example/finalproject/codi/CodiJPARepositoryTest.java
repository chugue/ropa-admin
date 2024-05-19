package com.example.finalproject.codi;


import com.example.finalproject.domain.codi.Codi;
import com.example.finalproject.domain.codi.CodiRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class CodiJPARepositoryTest {
    @Autowired
    private CodiRepository codiRepo;

    @Test
    public void search_test() {
        // given
        String keyword = "입니다";
        // when
        List<Codi> codiList =  codiRepo.findItemsByCodiDescription("%"+keyword+"%");
        // eye
        System.out.println("============   " + codiList.size() + "  ================================");
        // then

    }
}
