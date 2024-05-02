package com.example.finalproject.domain.items;

import com.example.finalproject.domain.category.Category;
import lombok.Data;
import org.hibernate.Hibernate;

import java.util.List;

public class ItemsResponse {

    //브랜드의 상품 목록 보기
    @Data
    public static class ItemsListDTO{
        private Integer itemId;
        private String itemName;
        private Integer price;
        private String mainCategory;
        private Integer stock;


        public ItemsListDTO(Items items) {
            this.itemId = items.getId();
            this.itemName = items.getName();
            this.price = items.getPrice();
            this.mainCategory = items.getCategory().getMain();
            this.stock = items.getStock();
        }
    }

}
