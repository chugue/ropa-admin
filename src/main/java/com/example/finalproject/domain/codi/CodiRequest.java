package com.example.finalproject.domain.codi;

import com.example.finalproject.domain.items.ItemsRequest;
import com.example.finalproject.domain.photo.Photo;
import lombok.Data;

import java.util.List;


public class CodiRequest {

    @Data
    public static class SaveDTO {
        private Integer userId;
        private String title;
        private String description;
        private List<AppSaveDTO> codiPhotos;
        private List<ItemCodiDTO> items;

        public SaveDTO(Integer userId, String title, String description, List<AppSaveDTO> codiPhotos, List<ItemCodiDTO> items) {
            this.userId = userId;
            this.title = title;
            this.description = description;
            this.codiPhotos = codiPhotos;
            this.items = items;
        }

        @Data
        public static class AppSaveDTO {
            private String photoName;
            private String photoBase64;
            private Boolean isMainPhoto;
            private Photo.Sort type;

            public AppSaveDTO(String photoName, String photoBase64, Boolean isMainPhoto, Photo.Sort type) {
                this.photoName = photoName;
                this.photoBase64 = photoBase64;
                this.isMainPhoto = isMainPhoto;
                this.type = type;
            }
        }

        @Data
        public static class ItemCodiDTO {
            private Integer brandId;
            private Integer itemsId;

            public ItemCodiDTO(Integer brandId, Integer itemsId) {
                this.brandId = brandId;
                this.itemsId = itemsId;
            }
        }
    }

}
