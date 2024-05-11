package com.example.finalproject.domain.items;

import com.example.finalproject.domain.photo.Photo;
import lombok.Data;

import java.util.List;

public class ItemsResponse {
    //크리에이터의 아이템 상세페이지
    @Data
    public static class ItemDetail {
        private Integer itemId;
        private String brandName;
        private Integer price;
        private Double discountRate;
        private Integer discountPrice;
        private List<MainPhoto> mainPhotos;
        private List<DetailPhoto> detailPhotos;

        public ItemDetail(Items item, List<Photo> photos, List<Photo> detailPhotos) {
            this.itemId = item.getId();
            this.brandName = item.getAdmin().getBrandName();
            this.discountRate = item.getDiscountRate();
            this.discountPrice = item.getDiscountPrice();
            this.price = item.getPrice();
            this.mainPhotos = photos.stream().map(MainPhoto::new).toList();
            this.detailPhotos = detailPhotos.stream().map(DetailPhoto::new).toList();
        }

        @Data
        public class MainPhoto {
            private Integer photoId;
            private String mainPhotoName;
            private String mainPhotoBase64;
            private Photo.Sort sort;

            public MainPhoto(Photo photo) {
                this.photoId = photo.getId();
                this.mainPhotoName = photo.getUuidName();
                this.mainPhotoBase64 = photo.toBase64(photo);
                this.sort = photo.getSort();
            }
        }

        @Data
        public class DetailPhoto {
            private Integer itemPhotoId;
            private String subPhotoName;
            private String subPhotoBase64;
            private Boolean isMainPhoto;
            private Photo.Sort sort;

            public DetailPhoto(Photo photo) {
                this.itemPhotoId = photo.getId();
                this.subPhotoName = photo.getUuidName();
                this.subPhotoBase64 = photo.toBase64(photo);
                this.isMainPhoto = photo.getIsMainPhoto();
                this.sort = photo.getSort();
            }
        }
    }

    //브랜드의 상품 목록 보기
    @Data
    public static class list {
        private Integer itemId;
        private String itemName;
        private Integer price;
        private String mainCategory;
        private Integer stock;

        public list(Items items) {
            this.itemId = items.getId();
            this.itemName = items.getName();
            this.price = items.getPrice();
            this.mainCategory = items.getCategory().getMain();
            this.stock = items.getStock();
        }
    }

    // 아이템 상세보기
    @Data
    public static class Detail {
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

        public Detail(Items items, List<Photo> itemPhotos) {
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
