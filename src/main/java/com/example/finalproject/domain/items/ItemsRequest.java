package com.example.finalproject.domain.items;

import com.example.finalproject.domain.admin.Admin;
import com.example.finalproject.domain.category.Category;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

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
        private Boolean status; // 삭제 상태
        private MultipartFile mainImage;
        private MultipartFile detailImage;

        public SaveDTO(Admin admin, String name, String description, String size, Integer price, Integer discountPrice, Boolean status, Integer stock, String mainCategory, String subCategory, MultipartFile mainImage, MultipartFile detailImage) {
            this.admin = admin;
            this.name = name;
            this.description = description;
            this.size = size;
            this.price = price;
            this.discountPrice = discountPrice;
            this.stock = stock;
            this.mainCategory = mainCategory;
            this.subCategory = subCategory;
            this.status = status;
            this.mainImage = mainImage;
            this.detailImage = detailImage;
        }


        public Items toEntity(Admin admin) {
            Items items = Items.builder()
                    .admin(admin)
                    .name(name)
                    .description(description)
                    .size(size)
                    .price(price)
                    .discountPrice(discountPrice)
                    .stock(stock)
                    .status(true)
                    .build();

            // 카테고리 생성
            Category category = Category.builder()
                    .main(mainCategory)
                    .sub(subCategory)
                    .build();
            items.setCategory(category);

            return items;
        }
    }

    // 아이템 수정(카테고리 포함)
    @Data
    public static class UpdateDTO {
        private Integer id;
        private Admin admin;
        private String name;
        private String description;
        private String size;
        private Integer price;
        private Integer discountPrice;
        private Integer stock;
        private String mainCategory; // 메인 카테고리
        private String subCategory;  // 서브 카테고리
        private MultipartFile mainImage;
        private MultipartFile detailImage;

        public UpdateDTO(Integer id, Admin admin, String name, String description, String size, Integer price, Integer discountPrice, Integer stock, String mainCategory, String subCategory, MultipartFile mainImage, MultipartFile detailImage) {
            this.id = id;
            this.admin = admin;
            this.name = name;
            this.description = description;
            this.size = size;
            this.price = price;
            this.discountPrice = discountPrice;
            this.stock = stock;
            this.mainCategory = mainCategory;
            this.subCategory = subCategory;
            this.mainImage = mainImage;
            this.detailImage = detailImage;
        }

        public Items toEntity() {
            Items items = Items.builder()
                    .id(id)
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
                    .build();

            // 아이템에 카테고리 설정
            items.setCategory(category);

            return items;
        }
    }
}
