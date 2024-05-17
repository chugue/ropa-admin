package com.example.finalproject.domain.photo;

import com.example.finalproject.domain.codi.Codi;
import com.example.finalproject.domain.items.Items;
import lombok.Data;

import java.util.List;

public class PhotoResponse {

    @Data
    public static class GetSearchPage{
        private List<CodiPhoto> codiPhotos;
        private List<ItemPhoto> itemPhotos;

        public GetSearchPage(List<Codi> codiPhotos, List<Items> itemsPhotos) {
            this.codiPhotos = codiPhotos.stream().map(CodiPhoto::new).toList();
            this.itemPhotos = itemsPhotos.stream().map(ItemPhoto::new).toList();
        }
        @Data
        private class CodiPhoto{
            private Integer photoId;
            private Integer codiId;
            private String photoPath;

            public CodiPhoto(Codi codi) {
                this.photoId = codi.getPhotos().getFirst().getId();
                this.codiId = codi.getId();
                this.photoPath = codi.getPhotos().getFirst()
                        .getPath();
            }
        }

        @Data
        private class ItemPhoto{
            private Integer itemsId;
            private String itemName;
            private String itemDescription;
            private Integer itemPrice;
            private Integer photoId;
            private String photoPath;

            public ItemPhoto(Items items) {
                this.itemsId = items.getId();
                this.itemName = items.getName();
                this.itemDescription = items.getDescription();
                this.itemPrice = items.getPrice();
                this.photoId = items.getPhotos().getFirst().getId();
                this.photoPath = items.getPhotos().getFirst().getPath();
            }
        }
    }

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
            private String photoPath;
            private Photo.Sort sort;
            private Integer creatorId;

            public CreatorPhoto(Photo photo) {
                this.photoId = photo.getId();
                this.creatorId = photo.getUser().getId();
                this.name = photo.getUuidName();
                this.photoPath = photo.getPath();
                this.sort = photo.getSort();
            }
        }

        @Data
        public class ItemsPhoto {
            private Integer photoId;
            private String name;
            private Photo.Sort sort;
            private String photoPath;
            private Integer itemsId;
            private AdminInfo adminInfo;

            public ItemsPhoto(Photo photo, com.example.finalproject.domain.admin.Admin admin) {
                this.photoId = photo.getId();
                this.itemsId = photo.getItems().getId();
                this.name = photo.getItems().getName();
                this.photoPath = photo.getPath();
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
            private String photoPath;
            private Photo.Sort sort;
            private Boolean isMainPhoto;

            public CodiesPhoto(Photo photo) {
                this.photoId = photo.getId();
                this.codiId = photo.getCodi().getId();
                this.name = photo.getUuidName();
                this.photoPath = photo.getPath();
                this.sort = photo.getSort();
                this.isMainPhoto = photo.getIsMainPhoto();
            }
        }
    }
}
