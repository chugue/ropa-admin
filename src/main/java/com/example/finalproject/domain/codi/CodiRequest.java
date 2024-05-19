package com.example.finalproject.domain.codi;

import com.example.finalproject.domain.photo.Photo;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;


public class CodiRequest {

    @Data
    public static class SaveDTO {
        private Integer userId;

        @NotEmpty(message = "코디 설명을 작성 해야 합니다.")
        @Size(min = 1, max = 20, message = "코디 제목은 최소 1자 이상 최대 20자 이하여야 합니다")
        private String description;

        @NotNull(message = "코디 사진을 넣어 주셔야 합니다.")
        private List<AppSaveDTO> codiPhotos;

        @NotNull(message = "코디에 해당하는 상품들을 등록 해 주세요.")
        private List<ItemCodiDTO> items;

        public SaveDTO(Integer userId, String description, List<AppSaveDTO> codiPhotos, List<ItemCodiDTO> items) {
            this.userId = userId;
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
