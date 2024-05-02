package com.example.finalproject.codiItems;


import com.example.finalproject.domain.codiItems.CodiItemsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CodiItemsRepositoryTest {
    @Autowired
    private CodiItemsRepository codiItemsRepository;

    @Test
    public void findByCodiWithItems_test(){
        // given
        Integer codiId = 1;
        // when
        codiItemsRepository.findByCodiWithItems(codiId);
        // then

    }
}
