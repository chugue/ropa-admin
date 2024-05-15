package com.example.finalproject.domain.items;

import com.example.finalproject.domain.admin.Admin;
import com.example.finalproject.domain.category.Category;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

public class ItemsRequest {

    @Data
    public static class SaveDTO {
        @NotNull(message = "브랜드로 로그인 해 주시길 바랍니다.")
        private Admin admin;

        @NotEmpty(message = "상품에 이름은 공백일 수 없습니다.")
        @Size(min = 1, max = 20, message = "상품 이름은 최소 1자 이상 최대 20자 이하여야 합니다")
        private String name;

        @NotEmpty(message = "상품에 설명은 공백일 수 없습니다.")
        @Size(min = 1, max = 20, message = "상품 설명은 최소 1자 이상 최대 20자 이하여야 합니다")
        private String description;

        @Pattern(regexp = "^(S|M|L|XL)$", message = "상품 사이즈는 공백일 수 없습니다")
        private String size;

        @NotEmpty(message = "상품에 가격은 공백일 수 없습니다.")
        @Size(min = 1, max = 20, message = "상품 가격은 최소 1자 이상 최대 20자 이하여야 합니다")
        private Integer price;
        private Integer discountPrice;

        @NotEmpty(message = "상품에 재고는 공백일 수 없습니다.")
        @Size(min = 1, message = "상품 재고 숫자는 최소 1자 이상이여야 합니다.")
        private Integer stock;

        @Pattern(regexp = "^(상의|하의)$", message = "메인 카테고리는 공백일 수 없습니다")
        private String mainCategory; // 메인 카테고리

        @NotEmpty(message = "서브 카테고리는 공백일 수 없습니다.")
        @Size(min = 1, max = 10, message = "카테고리는 최소 1자 이상 최대 10자 이하여야 합니다")
        private String subCategory;  // 서브 카테고리

        private Boolean status; // 삭제 상태

        @NotEmpty(message = "상품 사진은 공백일 수 없습니다.")
        private MultipartFile mainImage;

        @NotEmpty(message = "상품 사진은 공백일 수 없습니다.")
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

        @NotEmpty(message = "상품에 이름은 공백일 수 없습니다.")
        @Size(min = 1, max = 20, message = "상품 이름은 최소 1자 이상 최대 20자 이하여야 합니다")
        private String name;

        @NotEmpty(message = "상품에 설명은 공백일 수 없습니다.")
        @Size(min = 1, max = 20, message = "상품 설명은 최소 1자 이상 최대 20자 이하여야 합니다")
        private String description;

        @Pattern(regexp = "^(S|M|L|XL)$", message = "상품 사이즈는 공백일 수 없습니다")
        private String size;

        @NotEmpty(message = "상품에 가격은 공백일 수 없습니다.")
        @Size(min = 1, max = 20, message = "상품 가격은 최소 1자 이상 최대 20자 이하여야 합니다")
        private Integer price;
        private Integer discountPrice;

        @NotEmpty(message = "상품에 재고는 공백일 수 없습니다.")
        @Size(min = 1, message = "상품 재고 숫자는 최소 1자 이상이여야 합니다.")
        private Integer stock;

        @Pattern(regexp = "^(상의|하의)$", message = "메인 카테고리는 공백일 수 없습니다")
        private String mainCategory; // 메인 카테고리

        @NotEmpty(message = "서브 카테고리는 공백일 수 없습니다.")
        @Size(min = 1, max = 10, message = "카테고리는 최소 1자 이상 최대 10자 이하여야 합니다")
        private String subCategory;  // 서브 카테고리

        @NotEmpty(message = "상품 사진은 공백일 수 없습니다.")
        private MultipartFile mainImage;

        @NotEmpty(message = "상품 사진은 공백일 수 없습니다.")
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
