package com.example.finalproject.domain.order;

import com.example.finalproject._core.utils.AppJwtUtil;
import com.example.finalproject.domain.MyRestDoc;
import com.example.finalproject.domain.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class OrderRestControllerTest extends MyRestDoc {

    private static String jwt;
    private ObjectMapper om = new ObjectMapper();


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

        String userJwt = AppJwtUtil.create(
                User.builder()
                        .id(2)
                        .email("limsiwan@example.com")
                        .password("1234")
                        .myName("limsiwan")
                        .blueChecked(false)
                        .build());

        OrderRequest.OrderPage reqDTO = new OrderRequest.OrderPage();
        reqDTO.setItemId(3);
        reqDTO.setCodiId(3);

        String reqBody = om.writeValueAsString(reqDTO);
        System.out.println("reqBody : " + reqBody);

        // when
        ResultActions actions = mvc.perform(
                post("/app/order-page")
                        .header("Authorization", "Bearer " + userJwt)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reqBody)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody :" + respBody);

        // then
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.success").value(true));
        actions.andExpect(jsonPath("$.response.orderId").value(2));
        actions.andExpect(jsonPath("$.response.name").value("임시완"));
        actions.andExpect(jsonPath("$.response.phone").value("010-9876-5432"));
        actions.andExpect(jsonPath("$.response.email").value("limsiwan@example.com"));
        actions.andExpect(jsonPath("$.response.address").value("경기도 분당구"));
        actions.andExpect(jsonPath("$.response.detailAddress").value("판교로 456번길"));
        actions.andExpect(jsonPath("$.response.deliveryRequest").value("배송 요청사항 없음"));
        actions.andExpect(jsonPath("$.response.isBaseAddress").value(false));
        actions.andExpect(jsonPath("$.response.cartInfos[0].cartId").value(3));
        actions.andExpect(jsonPath("$.response.cartInfos[0].itemId").value(3));
        actions.andExpect(jsonPath("$.response.cartInfos[0].itemName").value("CHAIN STITCH OPEN COLLAR HALF SHIRT_BLACK"));
        actions.andExpect(jsonPath("$.response.cartInfos[0].size").value("L"));
        actions.andExpect(jsonPath("$.response.cartInfos[0].quantity").value(1));
        actions.andExpect(jsonPath("$.response.cartInfos[0].price").value(55000));
        actions.andExpect(jsonPath("$.response.cartInfos[0].amount").value(55000));
        actions.andExpect(jsonPath("$.response.cartInfos[0].itemPhoto.photoId").value(34));
        actions.andExpect(jsonPath("$.response.cartInfos[0].itemPhoto.uuidName").value("uuid_아이템사진3"));
        actions.andExpect(jsonPath("$.response.cartInfos[0].itemPhoto.photoPath").value("/upload/items/item03/mainItemPhoto.jpg"));
        actions.andExpect(jsonPath("$.response.cartInfos[0].itemPhoto.isMainPhoto").value(true));
        actions.andExpect(jsonPath("$.response.cartInfos[1].cartId").value(4));
        actions.andExpect(jsonPath("$.response.cartInfos[1].itemId").value(4));
        actions.andExpect(jsonPath("$.response.cartInfos[1].itemName").value("나일론 립스탑 유틸리티 팬츠_BLACK"));
        actions.andExpect(jsonPath("$.response.cartInfos[1].size").value("M"));
        actions.andExpect(jsonPath("$.response.cartInfos[1].quantity").value(1));
        actions.andExpect(jsonPath("$.response.cartInfos[1].price").value(50000));
        actions.andExpect(jsonPath("$.response.cartInfos[1].amount").value(50000));
        actions.andExpect(jsonPath("$.response.cartInfos[1].itemPhoto.photoId").value(36));
        actions.andExpect(jsonPath("$.response.cartInfos[1].itemPhoto.uuidName").value("uuid_아이템사진4"));
        actions.andExpect(jsonPath("$.response.cartInfos[1].itemPhoto.photoPath").value("/upload/items/item04/mainItemPhoto.jpg"));
        actions.andExpect(jsonPath("$.response.cartInfos[1].itemPhoto.isMainPhoto").value(true));
        actions.andExpect(jsonPath("$.response.orderInfo.orderAmount").value(105000));
        actions.andExpect(jsonPath("$.response.orderInfo.deliveryFee").value(0));
        actions.andExpect(jsonPath("$.response.orderInfo.discount").value(0));
        actions.andExpect(jsonPath("$.response.orderInfo.purchaseAmount").value(105000));
        actions.andExpect(jsonPath("$.response.orderInfo.payMethod").value("NA"));
        actions.andExpect(jsonPath("$.response.orderInfo.savedPayMethod").value(false));
        actions.andExpect(jsonPath("$.errorMessage").doesNotExist());
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);

    }

    @Test
    public void order_page_fail_test() throws Exception {
        // given
        OrderRequest.OrderPage reqDTO = new OrderRequest.OrderPage();
        reqDTO.setItemId(999);
        reqDTO.setCodiId(3);

        String reqBody = om.writeValueAsString(reqDTO);
        System.out.println("reqBody : " + reqBody);

        // when
        ResultActions actions = mvc.perform(
                post("/app/order-page")
                        .header("Authorization", "Bearer " + jwt)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reqBody)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody :" + respBody);

        // then
        actions.andExpect(status().isNotFound()); // HTTP 상태 코드 검증
        actions.andExpect(jsonPath("$.status").value(404));
        actions.andExpect(jsonPath("$.success").value(false));
        actions.andExpect(jsonPath("$.response").doesNotExist());
        actions.andExpect(jsonPath("$.errorMessage").value("해당 아이템을 찾을 수 없습니다."));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
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
        reqDTO.setDetailAddress("테헤란로 123길");
        reqDTO.setDeliveryRequest("문 앞에 놓아주세요");
        reqDTO.setIsBaseAddress(true);
        reqDTO.setPurchaseInfo(purchaseInfo);

        String reqBody = om.writeValueAsString(reqDTO);
        System.out.println("reqBody : " + reqBody);

        // when
        ResultActions actions = mvc.perform(
                post("/app/order")
                        .header("Authorization", "Bearer " + jwt)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reqBody)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody :" + respBody);

        // then
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.success").value(true));
        actions.andExpect(jsonPath("$.response.savedOrder.orderId").value(6));
        actions.andExpect(jsonPath("$.response.savedOrder.userId").value(1));
        actions.andExpect(jsonPath("$.response.savedDelievery.deliveryId").value(6));
        actions.andExpect(jsonPath("$.response.savedDelievery.recipient").value("정해인"));
        actions.andExpect(jsonPath("$.response.savedDelievery.status").value("배송중"));
        actions.andExpect(jsonPath("$.response.deletedCarts[0].cartId").value(1));
        actions.andExpect(jsonPath("$.response.deletedCarts[0].itemId").value(1));
//        actions.andExpect(jsonPath("$.response.deletedCarts[1].cartId").value(2));
//        actions.andExpect(jsonPath("$.response.deletedCarts[1].itemId").value(2));
        actions.andExpect(jsonPath("$.response.savedOHList[0].orderHistoryId").value(13));
        actions.andExpect(jsonPath("$.response.savedOHList[0].adminId").value(1));
        actions.andExpect(jsonPath("$.response.savedOHList[0].orderId").value(6));
        actions.andExpect(jsonPath("$.response.savedOHList[0].itemsId").value(1));
//        actions.andExpect(jsonPath("$.response.savedOHList[1].orderHistoryId").value(14));
//        actions.andExpect(jsonPath("$.response.savedOHList[1].adminId").value(1));
//        actions.andExpect(jsonPath("$.response.savedOHList[1].orderId").value(6));
//        actions.andExpect(jsonPath("$.response.savedOHList[1].itemsId").value(2));
        actions.andExpect(jsonPath("$.errorMessage").doesNotExist());
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    @Test
    public void order_fail_test() throws Exception {

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
        reqDTO.setDetailAddress("테헤란로 123길");
        reqDTO.setDeliveryRequest("배송 요청사항 없음");
        reqDTO.setIsBaseAddress(true);
        reqDTO.setPurchaseInfo(purchaseInfo);

        String reqBody = om.writeValueAsString(reqDTO);
        System.out.println("reqBody : " + reqBody);

        // when
        ResultActions actions = mvc.perform(
                post("/app/order")
                        .header("Authorization", "Bearer " + jwt)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reqBody)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody :" + respBody);

        // then
        actions.andExpect(status().isBadRequest());
        actions.andExpect(jsonPath("$.status").value(400));
        actions.andExpect(jsonPath("$.success").value(false));
        actions.andExpect(jsonPath("$.response").doesNotExist());
        actions.andExpect(jsonPath("$.errorMessage").value("주문자 이름은 공백 일 수 없습니다. : name"));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }
}