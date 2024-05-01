package com.example.finalproject.domain.items;


import com.example.finalproject.domain.category.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ItemsService {
    private final ItemsRepository itemsRepository;


//    public List<ItemsResponse.ItemsListDTO> findItemsByAdminId(int adminId) {
//        List<Items> item = itemsRepository.findItemsByAdminId(adminId);
//
////        List<ItemsResponse.ItemsListDTO> respDTO = item.stream().map(items -> {
////          return new ItemsResponse.ItemsListDTO(items);
////        }).collect(Collectors.toList());
//
//        return respDTO;
//    }

}
