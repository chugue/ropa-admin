package com.example.finalproject.domain.order;

import com.example.finalproject._core.error.exception.Exception401;
import com.example.finalproject._core.error.exception.Exception404;
import com.example.finalproject.domain.admin.Admin;
import com.example.finalproject.domain.admin.AdminRepository;
import com.example.finalproject.domain.cart.Cart;
import com.example.finalproject.domain.cart.CartRepository;
import com.example.finalproject.domain.delivery.Delivery;
import com.example.finalproject.domain.delivery.DeliveryRepository;
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
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final OrderHistoryRepository orderHistoryRepository;
    private final DeliveryRepository deliveryRepository;
    private final AdminRepository adminRepository;

    // TODO : 배송지 정보 저장 체크해서 true일경우 조회해서 뿌리고 false면은 칸 비워주기
    // 주문 + 배송지 + 결제 설정 페이지
    public OrderResponse.PageView orderPage(Integer userId) {
        // 배송지+결제+사용자 정보 가져오기
        List<Order> order = orderRepository.findByUserId(userId);

        // 장바구니 내역 불러오기
        List<Cart> cartList = cartRepository.findAllByUserIdAndMainPhoto(userId);

        return new OrderResponse.PageView(order, cartList);
    }

    // 주문하기
    @Transactional
    public void saveOrder(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception401("사용자 정보를 찾을 수 없습니다."));
        // 사용자의 장바구니에서 아이템들을 가져옴
        List<Cart> cartItems = cartRepository.findAllByUserId(userId);

        // 주문 생성
        Order order = OrderRequest.SaveDTO.toEntity(user, cartItems);

        // 장바구니에서 주문내역으로 이동
        for (Cart cartItem : cartItems) {
            OrderHistory orderHistory = OrderHistory.builder()
                    .admin(cartItem.getItems().getAdmin())
                    .order(order)
                    .items(cartItem.getItems())
                    .orderItemQty(cartItem.getQuantity())
                    .orderItemPrice(cartItem.getTotalAmount())
                    .build();
            // 주문 내역 저장
            orderHistoryRepository.save(orderHistory);
        }

        // 주문 저장
        orderRepository.save(order);

        // 장바구니 비우기
        cartRepository.deleteAll(cartItems);
    }

    // 주문 + 배송지 + 결제 정보 저장
    @Transactional
    public OrderResponse.SaveOrder saveOrder(OrderRequest.SaveOrder reqDTO, Integer userId) {
        // 사용자 정보 찾기
        User user = userRepository.findById(userId).orElseThrow(() ->
                new Exception404("사용자 정보를 찾을 수 없습니다."));
        // 사용자 아이디로 모든 카트 찾기
        List<Cart> carts = cartRepository.findAllByUserIdWithAdmin(userId);

        // 배송지 정보 저장 => 주문하면 배송중 상태 => 배송전 상태가 있으면 구현할 로직 많아짐
        // 관리자가 배송전 상태 체크해서 확인눌려서 배송중 상태 만들어야함.
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

        // Order 테이블 여기서 생성 결제 + 배송정보 같이 저장
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
            orderHistories.add(orderHistoryRepository.save(OrderHistory.builder()
                    .admin(cart.getItems().getAdmin())
                    .order(order)
                    .items(cart.getItems())
                    .orderItemPrice(cart.getTotalAmount())
                    .orderItemQty(cart.getQuantity())
                    .fee(order.getFee()).build()));

            List<Integer> creatorIds = userRepository.findCreatorByItemId(cart.getItems().getId());
            creatorIds.forEach(creatorId -> {
                Optional<User> OptionalCreator = userRepository.findById(creatorId);
                Optional<Admin> optionalAdmin = adminRepository.findFirst();

                if (OptionalCreator.isPresent() && optionalAdmin.isPresent()) {
                    User creatorUser = OptionalCreator.get();
                    // 크리에이터의 마일리지 업데이트
                    int commissionUser = (int) (cart.getTotalAmount() * 0.05); // 수수료 계산
                    creatorUser.setMileage(creatorUser.getMileage() + commissionUser);
                    userRepository.save(creatorUser);

                    Admin admin = optionalAdmin.get();
                    int commissionAdmin = (int) (cart.getTotalAmount() * 0.05); // 수수료 계산
                    admin.setMileage(admin.getMileage() + commissionAdmin);
                    adminRepository.save(admin);
                } else {
                    if (optionalAdmin.isPresent()) {
                        Admin admin = optionalAdmin.get();
                        int commissionAdmin = (int) (cart.getTotalAmount() * 0.1); // 수수료 계산
                        admin.setMileage(admin.getMileage() + commissionAdmin);
                        adminRepository.save(admin);
                    }
                }
            });
        });


        // 카트 비우기
        cartRepository.deleteAll(carts);

        return new OrderResponse.SaveOrder(order, delivery, carts, orderHistories);
    }
}
