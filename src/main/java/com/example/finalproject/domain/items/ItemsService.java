package com.example.finalproject.domain.items;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ItemsService {
    private final ItemsRepository itemsRepository;


    public void findAllItemsWithInfo() {
//        itemsRepository.findAllWithInfo();
    }
}
