package com.example.finalproject.items;

import com.example.finalproject.domain.items.Items;
import com.example.finalproject.domain.items.ItemsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class ItemsRepositoryTest {
    @Autowired
    private ItemsRepository itemsRepository;

//    @Test
//    public void findItemsByAdminAndPhotos_test() {
//        // given
//        int itemId = 1;
//        // when
//        Items resp = itemsRepository.findItemsByAdminAndPhotos(itemId);
//        // then
//        System.out.println("1 번 아이템의 정보들 : " + resp);
//    }

    @Test
    public void findItemsByAdminId_test() {
        // given
        int adminId = 1;
        // when
        List<Items> respList = itemsRepository.findItemsByAdminId(adminId);
        // then
        respList.forEach(System.out::println);
    }
}
