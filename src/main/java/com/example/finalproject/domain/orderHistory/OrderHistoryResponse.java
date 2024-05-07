package com.example.finalproject.domain.orderHistory;

import com.example.finalproject.domain.delivery.Delivery;
import com.example.finalproject.domain.deliveryAddress.DeliveryAddress;
import com.example.finalproject.domain.user.User;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.List;

public class OrderHistoryResponse {

    // 전체 주문 내역 DTO (관리자)
    @Data
    public static class orderListDTO {
        private Integer orderId;
        private String userName;
        private String userPhone;
        private Integer itemId;
        private String itemName;
        private Integer price;
        private Integer count;
        private Integer totalPrice;
        private String orderDate;

        public orderListDTO(OrderHistory orderHistory) {
            this.orderId = orderHistory.getId();
            this.userName = orderHistory.getOrder().getUser().getMyName();
            this.userPhone = orderHistory.getOrder().getUser().getMobile();
            this.itemId = orderHistory.getItems().getId();
            this.itemName = orderHistory.getItems().getName();
            this.price = orderHistory.getItems().getPrice();
            this.count = orderHistory.getOrderItemQty();
            this.totalPrice = orderHistory.getOrderItemPrice();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            this.orderDate = dateFormat.format(orderHistory.getOrder().getOrderDate());
        }
    }

    // 배송 목록 DTO (관리자)
    @Data
    public static class DeliveryListDTO {
        private Integer orderId; // 주문 코드
        private String userName; // 주문자
        private String recipient; // 수령인
        private String recipientPhoneNumber; // 수령인 연락처
        private String status; // 배송현황
        private String orderDate; // 주문일자
        private String endDate; // 배송도착일자

        public DeliveryListDTO(OrderHistory orderHistory, User user, DeliveryAddress deliveryAddress, Delivery delivery) {
            this.orderId = orderHistory.getOrder().getId();
            this.userName = user.getMyName();
            this.recipient = deliveryAddress.getRecipient();
            this.recipientPhoneNumber = deliveryAddress.getPhoneNumber();
            this.status = delivery.getStatus();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            this.orderDate = dateFormat.format(delivery.getStartDate());
            this.endDate = dateFormat.format(delivery.getEndDate());
        }
    }

    // 주문내역 DTO (사용자)
    @Data
    public static class UserOrderHistoryDTO {
        private Integer userId;
        private List<ItemHistoryDTO> itemHistoryDTOList;

        public UserOrderHistoryDTO(Integer userId, List<ItemHistoryDTO> itemHistoryDTOList) {
            this.userId = userId;
            this.itemHistoryDTOList = itemHistoryDTOList;
        }
    }

    // 주문내역 아이템 DTO (사용자)
    @Data
    public static class ItemHistoryDTO {
        private Integer orderId; // 주문 코드
        private Integer itemId; // 주문자
        private String itemName; // 아이템명
        private String itemPhotos; // 아이템 사진
        private Integer itemCount; // 하나의 아이템 개수
        private Integer itemPrice; // 하나의 아이템 금액
        private Integer itemTotalPrice; // 하나의 아이템 총 금액
        private String itemCategoryMain; // 아이템 카테고리 main
        private String deliveryStatus; // 배송 현황

        public ItemHistoryDTO(OrderHistory orderHistory) {
            this.orderId = orderHistory.getOrder().getId();
            this.itemId = orderHistory.getItems().getId();
            this.itemName = orderHistory.getItems().getName();
            this.itemPhotos = orderHistory.getItems().getPhotos().getFirst().getPath();
            this.itemCount = orderHistory.getOrderItemQty();
            this.itemPrice = orderHistory.getItems().getPrice();
            this.itemTotalPrice = (this.itemCount * this.itemPrice);
            this.itemCategoryMain = orderHistory.getItems().getCategory().getMain();
            this.deliveryStatus = orderHistory.getOrder().getDelivery().getStatus();
        }
    }
}