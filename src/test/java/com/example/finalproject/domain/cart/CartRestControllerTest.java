package com.example.finalproject.domain.cart;

import com.example.finalproject._core.utils.AppJwtUtill;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class CartRestControllerTest {

    private ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

    private static String jwt;

    @BeforeAll
    public static void setUp() {
        jwt = AppJwtUtill.create(
                User.builder()
                        .id(1)
                        .email("junghein@example.com")
                        .password("1234")
                        .myName("junghein")
                        .build());
    }

    // Add other test fields and methods here
    @Test
    public void GetCartList_Success_test() throws Exception {

        // when
        ResultActions actions = mvc.perform(
                get("/app/carts")
                        .header("Authorization", "Bearer " + jwt)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody : " + respBody);

        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.success").value(true));
        actions.andExpect(jsonPath("$.response.userId").value(1));
        actions.andExpect(jsonPath("$.response.cartList[0].cartId").value(1));
        actions.andExpect(jsonPath("$.response.cartList[0].itemId").value(1));
        actions.andExpect(jsonPath("$.response.cartList[0].itemName").value("SCRAPPED 티셔츠(WHITE)"));
        actions.andExpect(jsonPath("$.response.cartList[0].photoPath").value("/upload/items/item01/mainItemPhoto.jpg"));
        actions.andExpect(jsonPath("$.response.cartList[0].itemPrice").value(45000));
        actions.andExpect(jsonPath("$.response.cartList[0].quantity").value(2));
        actions.andExpect(jsonPath("$.response.cartList[0].totalItemPrice").value(90000));
    }

    @Test
    public void CartSave_Success_test() throws Exception {

        // given
        CartRequest.SaveDTO reqDTO = new CartRequest.SaveDTO();
        reqDTO.setItemId(5);
        reqDTO.setQuantity(10);

        String reqBody = om.writeValueAsString(reqDTO);
        System.out.println("reqBody : " + reqBody);

        // when
        ResultActions actions = mvc.perform(
                post("/app/carts/save")
                        .header("Authorization", "Bearer " + jwt)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reqBody)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody : " + respBody);

        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.success").value(true));
        actions.andExpect(jsonPath("$.response.itemId").value(5));
        actions.andExpect(jsonPath("$.response.quantity").value(10));
    }

    @Test
    public void CartSave_fail_test() throws Exception {

        // given
        CartRequest.SaveDTO reqDTO = new CartRequest.SaveDTO();
        reqDTO.setItemId(999);
        reqDTO.setQuantity(4);

        String reqBody = om.writeValueAsString(reqDTO);
        System.out.println("reqBody : " + reqBody);

        // when
        ResultActions actions = mvc.perform(
                post("/app/carts/save")
                        .header("Authorization", "Bearer " + jwt)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reqBody)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody : " + respBody);

        // then
        actions.andExpect(jsonPath("$.status").value(404));
        actions.andExpect(jsonPath("$.success").value(false));
        actions.andExpect(jsonPath("$.response").isEmpty());
    }

    @Test
    public void CartDelete_Success_test() throws Exception {

        // given
        int itemId = 2;

        // when
        ResultActions actions = mvc.perform(
                delete("/app/carts/" + itemId)
                        .header("Authorization", "Bearer " + jwt)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody : " + respBody);

        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.success").value(true));
    }

    @Test
    public void CartDelete_fail_test() throws Exception {

        // given
        int itemId = 999;

        // when
        ResultActions actions = mvc.perform(
                delete("/app/carts/" + itemId)
                        .header("Authorization", "Bearer " + jwt)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody : " + respBody);

        // then
        actions.andExpect(jsonPath("$.status").value(404));
        actions.andExpect(jsonPath("$.success").value(false));
    }
}