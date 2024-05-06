package com.example.finalproject.domain.items;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ItemsRestController {
    private final ItemsService itemsService;


}
