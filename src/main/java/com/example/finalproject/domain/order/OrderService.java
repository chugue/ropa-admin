package com.example.finalproject.domain.order;

import com.example.finalproject._core.error.exception.Exception401;
import com.example.finalproject._core.error.exception.Exception404;
import com.example.finalproject.domain.admin.Admin;
import com.example.finalproject.domain.cart.Cart;
import com.example.finalproject.domain.cart.CartRepository;
import com.example.finalproject.domain.codi.CodiRepository;
import com.example.finalproject.domain.delivery.Delivery;
import com.example.finalproject.domain.delivery.DeliveryRepository;
import com.example.finalproject.domain.items.ItemsRepository;
import com.example.finalproject.domain.orderHistory.OrderHistory;
import com.example.finalproject.domain.orderHistory.OrderHistoryRepository;
import com.example.finalproject.domain.user.User;
import com.example.finalproject.domain.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final OrderHistoryRepository orderHistoryRepository;
    private final DeliveryRepository deliveryRepository;
    private final ItemsRepository itemsRepository;
    private final CodiRepository codiRepository;


    // 주문 + 배송지 + 결제 설정 페이지
    public OrderResponse.PageView orderPage(Integer userId, OrderRequest.OrderPage reqDTO) {
        Cart cart = Cart.builder()
                .items(itemsRepository.findById(reqDTO.getItemId()).orElseThrow(() -> new Exception404("해당 아이템을 찾을 수 없습니다.")))
                .user(userRepository.findById(userId).orElseThrow(() -> new Exception404("사용자 정보를 찾을 수 없습니다.")))
                .build();
        if (reqDTO.getCodiId() != null) {
            cart.setCodi(codiRepository.findById(reqDTO.getCodiId()).orElse(null));
        }

        // 배송지+결제+사용자 정보 가져오기
        List<Order> order = orderRepository.findByUserId(userId);
        // 장바구니 내역 불러오기
        List<Cart> cartList = cartRepository.findAllByUserIdAndMainPhoto(userId);

        return new OrderResponse.PageView(order, cartList);
    }

    // 주문 + 배송지 + 결제 정보 저장
    @Transactional
    public OrderResponse.SaveOrder saveOrder(OrderRequest.SaveOrder reqDTO, Integer userId) {
        // 사용자 정보 찾기
        User user = userRepository.findById(userId).orElseThrow(() ->
                new Exception401("사용자 정보를 찾을 수 없습니다."));
        // 사용자 아이디로 모든 카트 찾기
        List<Cart> carts = cartRepository.findAllByUserIdWithAdmin(userId);

        // 배송지 정보 저장
        Delivery delivery = deliveryRepository.save(Delivery.builder()
                .recipient(reqDTO.getName())
                .postalCode(reqDTO.getPostCode())
                .address(reqDTO.getAddress())
                .addressDetail(reqDTO.getDetailAddress())
                .phoneNumber(reqDTO.getPhone())
                .deliveryRequest(reqDTO.getDeliveryRequest())
                .status("배송중")
                .isBaseAddress(reqDTO.getIsBaseAddress())
                .startDate(Timestamp.from(Instant.now()))
                .build());

        // Order 테이블 생성
        Order order = orderRepository.save(Order.builder()
                .user(user)
                .delivery(delivery)
                .deliveryType(reqDTO.getPurchaseInfo().getDeliveryType())
                .payMethod(reqDTO.getPurchaseInfo().getPayMethod())
                .savePayMethod(reqDTO.getPurchaseInfo().getSavedPayMethod())
                .purchaseAmount(reqDTO.getPurchaseInfo().getPurchaseAmount())
                .fee(reqDTO.getPurchaseInfo().getPurchaseAmount() * 0.1)
                .orderDate(Timestamp.from(Instant.now())).build());

        List<OrderHistory> orderHistories = new ArrayList<>();

        // 카트를 OrderHistory테이블로 옮기기
        carts.forEach(cart -> {
            // 카트에 있는 아이템의 아이디 값과 코디에 등록된 아이템의 아이디 값을 비교하여 처리

            User creator;
            Admin admin;

            if (cart.getCodi() != null) {
                // 연동된 코디가 있을경우
                creator = cart.getCodi().getUser();
                admin = cart.getItems().getAdmin();

                int creatorMileage = (int) (cart.getTotalAmount() * 0.05);
                int brandMileage = (int) (cart.getTotalAmount() * 0.05);

                if (creator.getMileage() == null) {
                    creator.setMileage(0);
                }
                creator.setMileage(creator.getMileage() + creatorMileage);

                if (admin.getMileage() == null) {
                    admin.setMileage(0);
                }
                admin.setMileage(admin.getMileage() + brandMileage);
            } else {
                // 코디 아이템이 아닌 경우
                admin = cart.getItems().getAdmin();

                int brandMileage = (int) (cart.getTotalAmount() * 0.1);

                if (admin.getMileage() == null) {
                    admin.setMileage(0);
                }
                admin.setMileage(admin.getMileage() + brandMileage);
            }

            // OrderHistory 테이블에 저장
            orderHistories.add(orderHistoryRepository.save(OrderHistory.builder()
                    .admin(cart.getItems().getAdmin())
                    .order(order)
                    .items(cart.getItems())
                    .orderItemPrice(cart.getTotalAmount())
                    .orderItemQty(cart.getQuantity())
                    .fee(order.getFee()).build()));
        });

        // 카트 비우기
        cartRepository.deleteAll(carts);

        return new OrderResponse.SaveOrder(order, delivery, carts, orderHistories);
    }
}
