package com.example.finalproject.codiItems;


import com.example.finalproject.domain.codiItems.CodiItems;
import com.example.finalproject.domain.codiItems.CodiItemsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class CodiItemsRepositoryTest {
    @Autowired
    private CodiItemsRepository codiItemsRepository;

    @Test
    public void findByCodiWithItemsWithCategory_test(){
        // given
        Integer codiId = 1;
        // when
        List<CodiItems> codiItemsList = codiItemsRepository.findByCodiWithItems(codiId);
        // then
        System.out.println(codiItemsList.getFirst().getItems().getCategory().getMain());;

    }

    @Test
    public void findByUserWithCodiLIstItemsList_test() {
        // given
        Integer userId = 1;
        // when
        List<CodiItems> codiItems = codiItemsRepository.findCodiItemsByUserId(userId);
        // then
        System.out.println(codiItems.size());

    }

    @Test
    public void findByCodiWithItems_test() {
        // given
        Integer codiId = 1;
        // when
        List<CodiItems> codiItems = codiItemsRepository.findByCodiWithItems(codiId);
        // then
        codiItems.forEach(codiItems1 -> System.out.println("아이템 번호 : " + codiItems1.getItems().getId()));

    }
}
