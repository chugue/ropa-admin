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
            private String path;
            private Photo.Sort sort;
            private Integer creatorId;

            public CreatorPhoto(Photo photo) {
                this.photoId = photo.getId();
                this.creatorId = photo.getUser().getId();
                this.name = photo.getName();
                this.path = photo.getPath();
                this.sort = photo.getSort();
            }
        }

        @Data
        public class ItemsPhoto {
            private Integer photoId;
            private String name;
            private String path;
            private Photo.Sort sort;
            private Integer itemsId;
            private AdminInfo adminInfo;

            public ItemsPhoto(Photo photo, com.example.finalproject.domain.admin.Admin admin) {
                this.photoId = photo.getId();
                this.itemsId = photo.getItems().getId();
                this.name = photo.getName();
                this.path = photo.getPath();
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
            private String path;
            private Photo.Sort sort;

            public CodiesPhoto(Photo photo) {
                this.photoId = photo.getId();
                this.name = photo.getName();
                this.path = photo.getPath();
                this.sort = photo.getSort();
                this.codiId = photo.getId();
            }
        }
    }
}
