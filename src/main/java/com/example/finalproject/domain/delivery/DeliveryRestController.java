package com.example.finalproject.domain.delivery;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DeliveryRestController {
    private final DeliveryRepository deliveryRepository;

}
