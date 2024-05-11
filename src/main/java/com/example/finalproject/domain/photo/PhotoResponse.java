package com.example.finalproject.domain.photo;

import lombok.Data;

import java.util.List;

public class PhotoResponse {

    @Data
    public static class Home {
        private List<CreatorPhoto> popularUserPhotos;
        private List<ItemsPhoto> popularItemsPhotos;
        private List<CodiesPhoto> popularCodiPhotos;

        public Home(List<Photo> popularUserPhotos, List<Photo> popularItemsPhotos, List<Photo> popularCodiPhotos) {
            this.popularUserPhotos = popularUserPhotos.stream().map(CreatorPhoto::new).toList();
            this.popularItemsPhotos = popularItemsPhotos.stream().map(photo ->
                    new ItemsPhoto(photo, photo.getItems().getAdmin())).toList();
            this.popularCodiPhotos = popularCodiPhotos.stream().map(CodiesPhoto::new).toList();
        }

        @Data
        public class CreatorPhoto {
            private Integer photoId;
            private String name;
            private String base64;
            private Photo.Sort sort;
            private Integer creatorId;

            public CreatorPhoto(Photo photo) {
                this.photoId = photo.getId();
                this.creatorId = photo.getUser().getId();
                this.name = photo.getUuidName();
                this.base64 = photo.toBase64(photo);
                this.sort = photo.getSort();
            }
        }

        @Data
        public class ItemsPhoto {
            private Integer photoId;
            private String name;
            private Photo.Sort sort;
            private String base64;
            private Integer itemsId;
            private AdminInfo adminInfo;

            public ItemsPhoto(Photo photo, com.example.finalproject.domain.admin.Admin admin) {
                this.photoId = photo.getId();
                this.itemsId = photo.getItems().getId();
                this.name = photo.getUuidName();
                this.base64 = photo.toBase64(photo);
                this.sort = photo.getSort();
                this.adminInfo = new AdminInfo(admin);
            }

            @Data
            public class AdminInfo {
                private Integer brandId;
                private String brandName;

                public AdminInfo(com.example.finalproject.domain.admin.Admin admin) {
                    this.brandId = admin.getId();
                    this.brandName = admin.getBrandName();
                }
            }
        }

        @Data
        public class CodiesPhoto {
            private Integer photoId;
            private Integer codiId;
            private String name;
            private String base64;
            private Photo.Sort sort;
            private Boolean isMainPhoto;

            public CodiesPhoto(Photo photo) {
                this.photoId = photo.getId();
                this.codiId = photo.getCodi().getId();
                this.name = photo.getUuidName();
                this.base64 = photo.toBase64(photo);
                this.sort = photo.getSort();
                this.isMainPhoto = photo.getIsMainPhoto();
            }
        }
    }

    @Data
    public static class SearchPage {
        private List<CodiPhoto> codiPhotos;
        private List<ItemInfo> itemPhotos;

        public SearchPage( List<Photo> codiPhotos , List<Photo> itemPhotos) {
            this.codiPhotos = codiPhotos.stream().map(CodiPhoto::new).toList();
            this.itemPhotos = itemPhotos.stream().map(ItemInfo::new).toList();
        }

        @Data
        private class CodiPhoto {
            private Integer photoId;
            private Integer codiId;
            private String photoName;
            private String base64;

            public CodiPhoto(Photo photo) {
                this.photoId = photo.getId();
                this.codiId = photo.getCodi().getId();
                this.photoName = photo.getUuidName();
                this.base64 = photo.toBase64(photo);
            }
        }

        @Data
        private class ItemInfo {
            private Integer itemId;
            private String name;
            private String description;
            private Integer price;
            private ItemPhoto itemPhoto;

            public ItemInfo(Photo photo) {
                this.itemId = photo.getItems().getId();
                this.name = photo.getItems().getName();
                this.description = photo.getItems().getDescription();
                this.price = photo.getItems().getPrice();
                this.itemPhoto = new ItemPhoto(photo) ;
            }

            @Data
            public class ItemPhoto {
                private Integer photoId;
                private String photoName;
                private String base64;

                public ItemPhoto(Photo photo) {
                    this.photoId = photo.getId();
                    this.photoName = photo.getUuidName();
                    this.base64 = photo.toBase64(photo);
                }
            }
        }
    }
}
