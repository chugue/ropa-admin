package com.example.finalproject.delivery;

import com.example.finalproject.domain.delivery.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class DeliveryRepositoryTest {
    @Autowired
    private DeliveryRepository deliveryRepository;

}
