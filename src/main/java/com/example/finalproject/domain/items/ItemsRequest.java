package com.example.finalproject.domain.items;

import com.example.finalproject.domain.admin.Admin;
import com.example.finalproject.domain.category.Category;
import lombok.Data;

public class ItemsRequest {

    @Data
    public static class SaveDTO {
        private Admin admin;
        private String name;
        private String description;
        private String size;
        private Integer price;
        private Integer discountPrice;
        private Integer stock;
        private String mainCategory; // 메인 카테고리
        private String subCategory;  // 서브 카테고리

        public Items toEntity(Admin admin) {
            Items items = Items.builder()
                    .admin(admin)
                    .name(name)
                    .description(description)
                    .size(size)
                    .price(price)
                    .discountPrice(discountPrice)
                    .stock(stock)
                    .build();

            // 카테고리 생성
            Category category = Category.builder()
                    .main(mainCategory)
                    .sub(subCategory)
                    .items(items)
                    .build();
            items.setCategory(category);

            return items;
        }
    }
}
