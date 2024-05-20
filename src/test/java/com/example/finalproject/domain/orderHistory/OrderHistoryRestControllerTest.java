package com.example.finalproject.domain.orderHistory;

import com.example.finalproject._core.utils.AppJwtUtil;
import com.example.finalproject.domain.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class OrderHistoryRestControllerTest {

    private static String jwt;

    private ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

    @BeforeAll
    public static void setUp() {
        jwt = AppJwtUtil.create(
                User.builder()
                        .id(1)
                        .email("junghein@example.com")
                        .password("1234")
                        .myName("junghein")
                        .build());
    }

    @Test
    public void getOrderHistories_success_test() throws Exception {
        // given

        // when
        ResultActions actions = mvc.perform(
                get("/app/order-histories")
                        .header("Authorization", "Bearer " + jwt)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody : " + respBody);

        // then
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.success").value(true));
        actions.andExpect(jsonPath("$.response.userId").value(1));

        // 주문 이력 목록을 검증
        actions.andExpect(jsonPath("$.response.itemHistoryDTOList").isArray());
        // 주문 이력 목록에 두 개의 항목이 있는지 확인 (가정: 두 개의 항목이 있는 경우)
        actions.andExpect(jsonPath("$.response.itemHistoryDTOList.length()").value(2));

        // 첫 번째 항목을 검증
        actions.andExpect(jsonPath("$.response.itemHistoryDTOList[0].orderId").value(1));
        actions.andExpect(jsonPath("$.response.itemHistoryDTOList[0].itemId").value(1));
        actions.andExpect(jsonPath("$.response.itemHistoryDTOList[0].itemName").value("SCRAPPED 티셔츠(WHITE)"));
        actions.andExpect(jsonPath("$.response.itemHistoryDTOList[0].itemCount").value(2));
        actions.andExpect(jsonPath("$.response.itemHistoryDTOList[0].itemPrice").value(45000));
        actions.andExpect(jsonPath("$.response.itemHistoryDTOList[0].itemTotalPrice").value(90000));
        actions.andExpect(jsonPath("$.response.itemHistoryDTOList[0].itemCategoryMain").value("상의"));
        actions.andExpect(jsonPath("$.response.itemHistoryDTOList[0].deliveryStatus").value("배송중"));

        // 두 번째 항목을 검증
        actions.andExpect(jsonPath("$.response.itemHistoryDTOList[1].orderId").value(1));
        actions.andExpect(jsonPath("$.response.itemHistoryDTOList[1].itemId").value(2));
        actions.andExpect(jsonPath("$.response.itemHistoryDTOList[1].itemName").value("scratch 블루 청바지"));
        actions.andExpect(jsonPath("$.response.itemHistoryDTOList[1].itemCount").value(1));
        actions.andExpect(jsonPath("$.response.itemHistoryDTOList[1].itemPrice").value(32000));
        actions.andExpect(jsonPath("$.response.itemHistoryDTOList[1].itemTotalPrice").value(32000));
        actions.andExpect(jsonPath("$.response.itemHistoryDTOList[1].itemCategoryMain").value("하의"));
        actions.andExpect(jsonPath("$.response.itemHistoryDTOList[1].deliveryStatus").value("배송중"));
    }

    @Test
    public void getOrderHistories_fail_test() throws Exception {
        // given
        String invalidJwt = "invalid_jwt_token";
        // when
        ResultActions actions = mvc.perform(
                get("/app/order-histories")
                        .header("Authorization", "Bearer " + invalidJwt)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody : " + respBody);

        // then
        actions.andExpect(status().isUnauthorized());
        actions.andExpect(jsonPath("$.status").value(401));
        actions.andExpect(jsonPath("$.success").value(false));
        actions.andExpect(jsonPath("$.message").doesNotExist());
        actions.andExpect(jsonPath("$.errorMessage").value("토큰이 유효하지 않습니다."));
    }
}