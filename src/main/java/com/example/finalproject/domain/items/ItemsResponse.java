package com.example.finalproject.domain.items;

import com.example.finalproject.domain.photo.Photo;
import lombok.Data;

import java.util.List;

public class ItemsResponse {

    //브랜드의 상품 목록 보기
    @Data
    public static class listDTO {
        private Integer itemId;
        private String itemName;
        private Integer price;
        private String mainCategory;
        private Integer stock;

        public listDTO(Items items) {
            this.itemId = items.getId();
            this.itemName = items.getName();
            this.price = items.getPrice();
            this.mainCategory = items.getCategory().getMain();
            this.stock = items.getStock();
        }
    }

    // 아이템 상세보기
    @Data
    public static class DetailDTO {
        private Integer itemId;
        private String name;
        private String description;
        private String size;
        private Integer price;
        private Integer discountPrice;
        private Integer stock;
        private String mainCategory;
        private String subCategory;
        private String itemMainImage;
        private String itemDetailImage;

        public DetailDTO(Items items, List<Photo> itemPhotos) {
            this.itemId = items.getId();
            this.name = items.getName();
            this.description = items.getDescription();
            this.size = items.getSize();
            this.price = items.getPrice();
            this.discountPrice = items.getDiscountPrice();
            this.stock = items.getStock();
            this.mainCategory = items.getCategory().getMain();
            this.subCategory = items.getCategory().getSub();
            itemPhotos.forEach(photo -> {
                if (photo.getIsMainPhoto()) {
                    this.itemMainImage = photo.getPath();
                } else {
                    this.itemDetailImage = photo.getPath();
                }
            });
        }
    }
}
