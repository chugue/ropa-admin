package com.example.finalproject.domain.items;

import lombok.Data;

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

    // 아이템 상세보기
    @Data
    public static class ItemsDetailDTO{
        private Integer itemId;
        private String name;
        private String description;
        private String size;
        private Integer price;
        private Integer discountPrice;
        private Integer stock;
        private String mainCategory;
        private String subCategory;

        public ItemsDetailDTO(Items items) {
            this.itemId = items.getId();
            this.name = items.getName();
            this.description = items.getDescription();
            this.size = items.getSize();
            this.price = items.getPrice();
            this.discountPrice = items.getDiscountPrice();
            this.stock = items.getStock();
            this.mainCategory = items.getCategory().getMain();
            this.subCategory = items.getCategory().getSub();
        }
    }
}
