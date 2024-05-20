package com.example.finalproject.domain.order;

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
                post("/app/order-page")
                        .header("Authorization", "Bearer " + jwt)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody :" + respBody);

//        // then
//        actions.andExpect(jsonPath("$.status").value(200));
//        actions.andExpect(jsonPath("$.success").value(true));
//        actions.andExpect(jsonPath("$.response.orderId").value(1));
//        actions.andExpect(jsonPath("$.response.phone").value("010-1234-5678"));
//        actions.andExpect(jsonPath("$.response.email").value("junghein@example.com"));
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
    @Test
    public void order_success_test() throws Exception {
        // given
        OrderRequest.SaveOrder.PurchaseInfo purchaseInfo = new OrderRequest.SaveOrder.PurchaseInfo();
        purchaseInfo.setOrderAmount(10000);
        purchaseInfo.setDeliveryType(Order.DeliveryType.FREE);
        purchaseInfo.setDeliveryFee(1000);
        purchaseInfo.setDiscount(0);
        purchaseInfo.setPurchaseAmount(10000);
        purchaseInfo.setPayMethod(Order.PayMethod.KAKAO);
        purchaseInfo.setSavedPayMethod(true);

        OrderRequest.SaveOrder reqDTO = new OrderRequest.SaveOrder();
        reqDTO.setName("정해인");
        reqDTO.setPhone("010-1234-5678");
        reqDTO.setEmail("junghein@example.com");
        reqDTO.setPostCode("1234");
        reqDTO.setAddress("서울특별시 강남구");
        reqDTO.setDetailAddress("테헤란로 123");
        reqDTO.setDeliveryRequest("문 앞에 놓아주세요");
        reqDTO.setIsBaseAddress(true);
        reqDTO.setPurchaseInfo(purchaseInfo);

        String df = om.writeValueAsString(reqDTO);
        System.out.println("df: " + df);

        // Mocking Admin repository

        // when
        ResultActions actions = mvc.perform(
                post("/app/order")
                        .header("Authorization", "Bearer " + jwt)
                        .content(om.writeValueAsString(reqDTO))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.response.savedOrder.orderId").value(6))
                .andExpect(jsonPath("$.response.savedOrder.userId").value(1))
                .andExpect(jsonPath("$.response.savedDelievery.deliveryId").value(6))
                .andExpect(jsonPath("$.response.savedDelievery.recipient").value("정해인"))
                .andExpect(jsonPath("$.response.savedDelievery.status").value("배송중"))
                .andExpect(jsonPath("$.response.deletedCarts[0].cartId").value(1))
                .andExpect(jsonPath("$.response.deletedCarts[0].itemId").value(1))
                .andExpect(jsonPath("$.response.deletedCarts[1].cartId").value(2))
                .andExpect(jsonPath("$.response.deletedCarts[1].itemId").value(2))
                .andExpect(jsonPath("$.response.savedOHList[0].orderHistoryId").value(13))
                .andExpect(jsonPath("$.response.savedOHList[0].adminId").value(4))
                .andExpect(jsonPath("$.response.savedOHList[0].orderId").value(6))
                .andExpect(jsonPath("$.response.savedOHList[0].itemsId").value(1))
                .andExpect(jsonPath("$.response.savedOHList[1].orderHistoryId").value(14))
                .andExpect(jsonPath("$.response.savedOHList[1].adminId").value(4))
                .andExpect(jsonPath("$.response.savedOHList[1].orderId").value(6))
                .andExpect(jsonPath("$.response.savedOHList[1].itemsId").value(2))
                .andExpect(jsonPath("$.errorMessage").isEmpty());
    }


    @Test
    public void order_valid_fail_test() throws Exception {
        // given
        OrderRequest.SaveOrder.PurchaseInfo purchaseInfo = new OrderRequest.SaveOrder.PurchaseInfo();
        purchaseInfo.setOrderAmount(10000);
        purchaseInfo.setDeliveryType(Order.DeliveryType.FREE);
        purchaseInfo.setDeliveryFee(1000);
        purchaseInfo.setDiscount(0);
        purchaseInfo.setPurchaseAmount(10000);
        purchaseInfo.setPayMethod(Order.PayMethod.KAKAO);
        purchaseInfo.setSavedPayMethod(true);

        OrderRequest.SaveOrder reqDTO = new OrderRequest.SaveOrder();
        reqDTO.setName("");
        reqDTO.setPhone("010-1234-5678");
        reqDTO.setEmail("junghein@example.com");
        reqDTO.setPostCode("1234");
        reqDTO.setAddress("서울특별시 강남구");
        reqDTO.setDetailAddress("테헤란로 123");
        reqDTO.setDeliveryRequest("문 앞에 놓아주세요");
        reqDTO.setIsBaseAddress(true);
        reqDTO.setPurchaseInfo(purchaseInfo);

        String df = om.writeValueAsString(reqDTO);
        System.out.println("df: " + df);

        // Mocking Admin repository

        // when
        ResultActions actions = mvc.perform(
                post("/app/order")
                        .header("Authorization", "Bearer " + jwt)
                        .content(om.writeValueAsString(reqDTO))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        actions.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.response").doesNotExist())
                .andExpect(jsonPath("$.errorMessage").value("주문자 이름은 공백 일 수 없습니다. : name"));
    }







}
