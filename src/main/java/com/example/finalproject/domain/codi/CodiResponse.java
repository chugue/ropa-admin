package com.example.finalproject.domain.codi;

import com.example.finalproject.domain.admin.Admin;
import com.example.finalproject.domain.items.Items;
import com.example.finalproject.domain.love.Love;
import com.example.finalproject.domain.photo.Photo;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

public class CodiResponse {


    @Data
    public static class UpdatePage {
        private Integer codiId;
        private String description;
        private List<CodiPhoto> codiPhotos;
        private List<CodiItemPhoto> codiItemPhotos;

        public UpdatePage(Codi codi, List<Photo> codiPhotos, List<Photo> codiItemPhotos) {
            this.codiId = codi.getId();
            this.description = codi.getDescription();
            this.codiPhotos = codiPhotos.stream().map(CodiPhoto::new).toList();
            this.codiItemPhotos = codiItemPhotos.stream().map(CodiItemPhoto::new).toList();
        }

        @Data
        public class CodiPhoto {
            private Integer photoId;
            private Integer codiId;
            private String photoPath;
            private Boolean isMainPhoto;
            private Photo.Sort sort;

            public CodiPhoto(Photo photo) {
                this.photoId = photo.getId();
                this.codiId = photo.getCodi().getId();
                this.photoPath = photo.getPath();
                this.isMainPhoto = photo.getIsMainPhoto();
                this.sort = photo.getSort();
            }
        }

        @Data
        public class CodiItemPhoto {
            private Integer photoId;
            private Integer itemId;
            private String photoPath;
            private String categoryName;
            private Photo.Sort sort;

            public CodiItemPhoto(Photo photo) {
                this.photoId = photo.getId();
                this.itemId = photo.getItems().getId();
                this.photoPath = photo.getPath();
                this.categoryName = photo.getItems().getCategory().getMain();
                this.sort = photo.getSort();
            }
        }

    }

    //코디 등록에 아이템 등록 클릭시 나오는 뷰 DTO
    @Data
    public static class BrandInfo {
        private Integer brandId;
        private Integer photoId;
        private String photoPath;
        private List<ItemInfo> itemInfo;

        public BrandInfo(Admin admin, List<Items> items) {
            this.brandId = admin.getId();
            this.photoId = admin.getPhoto().getId();
            this.photoPath = admin.getPhoto().getPath();
            this.itemInfo = items.stream().map(ItemInfo::new).toList();
        }

        @Data
        public static class ItemInfo {
            private Integer itemId;
            private String itemName;
            private String photoPath;

            public ItemInfo(Items items) {
                this.itemId = items.getId();
                this.itemName = items.getName();
                this.photoPath = items.getPhotos().getFirst().getPath();
            }
        }
    }

    // 코디 등록할 때 사용하는 DTO
    @Data
    public static class SavedCodi {
        private Integer codiId;
        private Integer userId;
        private Integer photoId;
        private String photoPath;

        public SavedCodi(Codi saveCodi, Photo photo) {
            this.codiId = saveCodi.getId();
            this.userId = saveCodi.getUser().getId();
            this.photoId = photo.getId();
            this.photoPath = photo.getPath();
        }
    }

    @Data
    public static class MainView {
        private Integer codiId;
        private String description;
        private String createdAt;
        private Boolean isloved;
        private Long loveCount;
        private List<MainPhoto> mainPhotos;
        private List<ItemsPhoto> itemPhotos;
        private List<CodiPhoto> otherCodiPhotos;

        public MainView(Codi codi, Optional<Love> codiLoveStatus, Long totalLoves, List<Photo> mainPhotos, List<Photo> itemPhotos, List<Photo> otherCodiPhotos) {
            this.codiId = codi.getId();
            this.description = codi.getDescription();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            this.createdAt = formatter.format(codi.getCreatedAt());
            if (codiLoveStatus.isEmpty()) {
                this.isloved = false;
            } else {
                this.isloved = codiLoveStatus.get().getIsLoved();
            }
            this.loveCount = totalLoves;
            this.mainPhotos = mainPhotos.stream().map(photo ->
                    new MainPhoto(photo)).toList();
            this.itemPhotos = itemPhotos.stream().map(photo ->
                    new ItemsPhoto(photo)).toList();
            this.otherCodiPhotos = otherCodiPhotos.stream().map(photo ->
                    new CodiPhoto(photo)).toList();
        }

        @Data
        public class MainPhoto {
            private Integer mainPhotoId;
            private String mainPhotoName;
            private String photoPath;
            private Boolean isMainPhoto;

            public MainPhoto(Photo mainPhoto) {
                this.mainPhotoId = mainPhoto.getId();
                this.mainPhotoName = mainPhoto.getUuidName();
                this.photoPath = mainPhoto.getPath();
                this.isMainPhoto = mainPhoto.getIsMainPhoto();
            }
        }

        @Data
        public class ItemsPhoto {
            private Integer itemsPhotoId;
            private Integer itemsId;
            private String itemsPhotoName;
            private String photoPath;
            private Boolean isMainPhoto;

            public ItemsPhoto(Photo photo) {
                this.itemsPhotoId = photo.getId();
                this.itemsId = photo.getItems().getId();
                this.itemsPhotoName = photo.getUuidName();
                this.photoPath = photo.getPath();
                this.isMainPhoto = photo.getIsMainPhoto();
            }
        }

        @Data
        public class CodiPhoto {
            private Integer codiId;
            private Integer codiPhotoId;
            private String codiPhotoName;
            private String photoPath;
            private Boolean isMainPhoto;

            public CodiPhoto(Photo photo) {
                this.codiId = photo.getCodi().getId();
                this.codiPhotoId = photo.getId();
                this.codiPhotoName = photo.getUuidName();
                this.photoPath = photo.getPath();
                this.isMainPhoto = photo.getIsMainPhoto();
            }
        }

    }

    @Data
    public static class OpenMainView {
        private Integer codiId;
        private String description;
        private String createdAt;
        private Boolean isloved;
        private Long loveCount;
        private List<MainPhoto> mainPhotos;
        private List<ItemsPhoto> itemPhotos;
        private List<CodiPhoto> otherCodiPhotos;

        public OpenMainView(Codi codi, Long totalLoves, List<Photo> mainPhotos, List<Photo> itemPhotos, List<Photo> otherCodiPhotos) {
            this.codiId = codi.getId();
            this.description = codi.getDescription();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            this.createdAt = formatter.format(codi.getCreatedAt());
            this.isloved = false;
            this.loveCount = totalLoves;
            this.mainPhotos = mainPhotos.stream().map(photo ->
                    new MainPhoto(photo)).toList();
            this.itemPhotos = itemPhotos.stream().map(photo ->
                    new ItemsPhoto(photo)).toList();
            this.otherCodiPhotos = otherCodiPhotos.stream().map(photo ->
                    new CodiPhoto(photo)).toList();
        }

        @Data
        public class MainPhoto {
            private Integer mainPhotoId;
            private String mainPhotoName;
            private String photoPath;
            private Boolean isMainPhoto;

            public MainPhoto(Photo mainPhoto) {
                this.mainPhotoId = mainPhoto.getId();
                this.mainPhotoName = mainPhoto.getUuidName();
                this.photoPath = mainPhoto.getPath();
                this.isMainPhoto = mainPhoto.getIsMainPhoto();
            }
        }

        @Data
        public class ItemsPhoto {
            private Integer itemsPhotoId;
            private Integer itemsId;
            private String itemsPhotoName;
            private String photoPath;
            private Boolean isMainPhoto;

            public ItemsPhoto(Photo photo) {
                this.itemsPhotoId = photo.getId();
                this.itemsId = photo.getItems().getId();
                this.itemsPhotoName = photo.getUuidName();
                this.photoPath = photo.getPath();
                this.isMainPhoto = photo.getIsMainPhoto();
            }
        }

        @Data
        public class CodiPhoto {
            private Integer codiPhotoId;
            private Integer codiId;
            private String codiPhotoName;
            private String photoPath;
            private Boolean isMainPhoto;

            public CodiPhoto(Photo photo) {
                this.codiPhotoId = photo.getId();
                this.codiId = photo.getCodi().getId();
                this.codiPhotoName = photo.getUuidName();
                this.photoPath = photo.getPath();
                this.isMainPhoto = photo.getIsMainPhoto();
            }
        }
    }

    // 코디 리스트 DTO
    @Data
    public static class CodiListDTO {
        private Integer codiId;
        private Integer codiPhotoId;
        private String photoName;
        private String photoPath;

        public CodiListDTO(Codi codi) {
            this.codiId = codi.getId();
            List<Photo> codiPhotos = codi.getPhotos();
            if (codiPhotos != null && !codiPhotos.isEmpty()) {
                Photo codiPhoto = codiPhotos.getFirst(); // 첫 번째 포토만 사용
                this.codiPhotoId = codiPhoto.getId();
                this.photoName = codiPhoto.getUuidName();
                this.photoPath = codiPhoto.getPath();
            }
        }
    }
}
