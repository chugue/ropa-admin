package com.example.finalproject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FinalprojectApplicationTests {

    @Test
    void contextLoads() {
        Integer price  = 10000;
        Double fee = 0.1;
        int t = (int) (price*fee);
        System.out.println("타입 확인: " + price*fee);
    }

}
