package com.example.finalproject.order;

import com.example.finalproject._core.utils.AppJwtUtil;
import com.example.finalproject.domain.admin.Admin;
import com.example.finalproject.domain.admin.AdminRepository;
import com.example.finalproject.domain.order.Order;
import com.example.finalproject.domain.order.OrderRequest;
import com.example.finalproject.domain.order.OrderResponse;
import com.example.finalproject.domain.user.SessionUser;
import com.example.finalproject.domain.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class OrderControllerTest {

    private ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AdminRepository adminRepository;

    private static String jwt;

    @BeforeAll
    public static void setUp() {
        jwt = AppJwtUtil.create(
                User.builder()
                        .id(1)
                        .email("junghein@example.com")
                        .password("1234")
                        .myName("junghein")
                        .blueChecked(false)
                        .build());
    }

    // 총 주문 페이지 + 배송지 설정 화면
    @Test
    public void order_page_test() throws Exception {
        // given

        // when
        ResultActions actions = mvc.perform(
                get("/app/order-page")
                        .header("Authorization", "Bearer " + jwt)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody :" + respBody);

        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.success").value(true));
        actions.andExpect(jsonPath("$.response.orderId").value(1));
        actions.andExpect(jsonPath("$.response.phone").value("010-1234-5678"));
        actions.andExpect(jsonPath("$.response.email").value("junghein@example.com"));
    }

    @Test
    public void order_page_fail_test() throws Exception {
        // given

        // when
        ResultActions actions = mvc.perform(
                get("/app/order-pages")
                        .header("Authorization", "Bearer " + jwt)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody :" + respBody);

        // then
        actions.andExpect(status().isNotFound()); // HTTP 상태 코드 검증
    }

    //주문
//    @Test
//    public void order_success_test() throws Exception {
//        // given
//        OrderRequest.SaveOrder.PurchaseInfo purchaseInfo = new OrderRequest.SaveOrder.PurchaseInfo(
//                10000, // orderAmount
//                Order.DeliveryType.FREE, // deliveryType
//                1000, // deliveryFee
//                0, // discount
//                10000, // purchaseAmount
//                Order.PayMethod.KAKAO, // payMethod
//                true // savedPayMethod
//        );
//
//        OrderRequest.SaveOrder reqDTO = new OrderRequest.SaveOrder(
//                "정해인", "010-1234-5678",
//                "junghein@example.com",
//                "1234",
//                "서울특별시 강남구",
//                "테헤란로 123길",
//                "문 앞에 놓아주세요",
//                true,
//                purchaseInfo
//        );
//
//        OrderResponse.SaveOrder respDTO = new OrderResponse.SaveOrder(
//                "order123", "정해인", 10000, Order.DeliveryType.FREE, Order.PayMethod.KAKAO, true
//        );
//
//        Admin admin = Admin.builder()
//                .id(1)
//                .email("admin@example.com")
//                .password("password")
//                .mileage(1000)
//                .build();
//
//        // Mocking Admin repository
//        when(adminRepository.findById(anyInt())).thenReturn(Optional.of(admin));
//
//        // Mocking session attribute and service method
//        SessionUser sessionUser = new SessionUser("user123", "정해인");
//        when(session.getAttribute("sessionUser")).thenReturn(sessionUser);
//        when(orderService.saveOrder(any(OrderRequest.SaveOrder.class), anyInt()))
//                .thenReturn(respDTO);
//
//        // when
//        ResultActions actions = mvc.perform(
//                post("/app/order")
//                        .header("Authorization", "Bearer " + jwt)
//                        .content(om.writeValueAsString(reqDTO))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .sessionAttr("sessionUser", sessionUser)
//        );
//
//        // then
//        actions.andExpect(status().isOk());
//        actions.andExpect(jsonPath("$.data.orderId").value("order123"));
//        actions.andExpect(jsonPath("$.data.name").value("정해인"));
//        actions.andExpect(jsonPath("$.data.amount").value(10000));
//        actions.andExpect(jsonPath("$.data.deliveryType").value(Order.DeliveryType.FREE.toString()));
//        actions.andExpect(jsonPath("$.data.payMethod").value(Order.PayMethod.KAKAO.toString()));
//        actions.andExpect(jsonPath("$.data.savedPayMethod").value(true));
//
//    }




}
