//package com.example.finalproject.domain.order;
//
//import com.example.finalproject.domain.cart.Cart;
//import com.example.finalproject.domain.items.Items;
//import com.example.finalproject.domain.photo.Photo;
//import lombok.Builder;
//import lombok.Data;
//
//import java.util.List;
//
//public class OrderResponse {
//
//    @Data
//    public static class PageView{
//        private Integer orderId;
//        private String name;
//        private String phone;
//        private String email;
//        private String address;
//        private String detailAddress;
//        private String deliveryRequest;
//        private Boolean isBaseAddress;
//        private List<OrderCartInfo> cartInfos;
//        private OrderInfo orderInfo;
//
//        @Builder
//        public PageView(Order order, List<Cart> carts) {
//            this.orderId = order.getId();
//            this.name = order.getDelivery().getRecipient();
//            this.phone = order.getDelivery().getPhoneNumber();
//            this.email = order.getUser().getEmail();
//            this.address = order.getDelivery().getAddress();
//            this.detailAddress = order.getDelivery().getAddressDetail();
//            this.deliveryRequest = order.getDelivery().getRequestMsg();
//            this.isBaseAddress = order.getDelivery().getIsBaseAddress();
//            this.cartInfos = cartInfos;
//            this.orderInfo = orderInfo;
//        }
//
//        @Data
//        public class OrderCartInfo {
//            private Integer cartId;
//            private Integer itemId;
//            private String itemName;
//            private String size;
//            private Integer quantity;
//            private Integer price;
//            private Integer amount;
//            private ItemPhoto itemPhoto;
//
//            public OrderCartInfo(Cart cart) {
//                this.cartId = cart.getId();
//                this.itemId = cart.getItems().getId();
//                this.itemName = cart.getItems().getName();
//                this.size = cart.getItems().getSize();
//                this.quantity = cart.getQuantity();
//                this.price = cart.getItems().getPrice();
//                this.amount = cart.getTotalAmount();
//                this.itemPhoto = new ItemPhoto(cart.getItems().getPhotos().getFirst());
//            }
//
//            @Data
//            public class ItemPhoto {
//                private Integer photoId;
//                private String photoTitle;
//                private String photoPath;
//                private Boolean isMainPhoto;
//
//                public ItemPhoto(Photo photo) {
//                    this.photoId = photo.getId();
//                    this.photoTitle = photo.getName();
//                    this.photoPath = photo.getPath();
//                    this.isMainPhoto = photo.getIsMainPhoto();
//                }
//            }
//        }
//
//    }
//}
