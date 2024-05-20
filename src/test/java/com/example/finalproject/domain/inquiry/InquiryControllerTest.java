package com.example.finalproject.domain.inquiry;

import com.example.finalproject._core.utils.AppJwtUtil;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class InquiryControllerTest {

    private ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

    private static String jwt;

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
    public void inquiryDetail_success_test() throws Exception {
        // given
        int inquiryId = 1;

        // when
        ResultActions actions = mvc.perform(
                get("/app/inquiries/" + inquiryId)
                        .header("Authorization", "Bearer " + jwt)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody : " + respBody);

        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.success").value(true));
        actions.andExpect(jsonPath("$.response.inquiryId").value(1));
        actions.andExpect(jsonPath("$.response.userId").value(1));
        actions.andExpect(jsonPath("$.response.title").value("상품 문의"));
        actions.andExpect(jsonPath("$.response.content").value("상품이 반팔도 셔츠도 입고 되면 좋겠는데  혹시 안 되나요?."));
        actions.andExpect(jsonPath("$.response.commentDTO.brandId").value(1));
        actions.andExpect(jsonPath("$.response.commentDTO.brandName").value("SALOMON"));
        actions.andExpect(jsonPath("$.response.commentDTO.comment").value("출시 예정 제품 있습니다! 1월 27일 11:00부로 상품 구매 가능합니다!"));
    }

    @Test
    public void inquiryDetail_fail_test() throws Exception {
        // given
        int inquiryId = 999;

        // when
        ResultActions actions = mvc.perform(
                get("/app/inquiries/" + inquiryId)
                        .header("Authorization", "Bearer " + jwt)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody : " + respBody);

        // then
        actions.andExpect(jsonPath("$.status").value(404));
        actions.andExpect(jsonPath("$.success").value(false));
        actions.andExpect(jsonPath("$.errorMessage").value("페이지를 찾을 수 업습니다."));
    }

    @Test
    public void inquirySave_success_test() throws Exception {
        // given
        InquiryRequest.SaveDTO reqDTO = new InquiryRequest.SaveDTO();
        reqDTO.setBrandId(3);
        reqDTO.setTitle("상품 문의");
        reqDTO.setContent("상품 문의 좀 할께요.");

        String reqBody = om.writeValueAsString(reqDTO);
        System.out.println("reqBody : " + reqBody);

        // when
        ResultActions actions = mvc.perform(
                post("/app/inquiries")
                        .header("Authorization", "Bearer " + jwt)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reqBody)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody : " + respBody);

        // then
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.success").value(true));
        actions.andExpect(jsonPath("$.response.brandId").value(3));
        actions.andExpect(jsonPath("$.response.title").value("상품 문의"));
        actions.andExpect(jsonPath("$.response.content").value("상품 문의 좀 할께요."));
    }

    @Test
    public void inquirySave_fail_test() throws Exception {
        // given
        InquiryRequest.SaveDTO reqDTO = new InquiryRequest.SaveDTO();
        reqDTO.setBrandId(999);
        reqDTO.setTitle("상품 문의");
        reqDTO.setContent("상품 문의 좀 할께요.");

        String reqBody = om.writeValueAsString(reqDTO);
        System.out.println("reqBody : " + reqBody);

        // when
        ResultActions actions = mvc.perform(
                post("/app/inquiries")
                        .header("Authorization", "Bearer " + jwt)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reqBody)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody : " + respBody);

        // then
        actions.andExpect(status().isNotFound());
        actions.andExpect(jsonPath("$.status").value(404));
        actions.andExpect(jsonPath("$.success").value(false));
        actions.andExpect(jsonPath("$.response").isEmpty());
    }

    @Test
    public void inquiryPage_success_test() throws Exception {

        // when
        ResultActions actions = mvc.perform(
                get("/app/inquiries-lists")
                        .header("Authorization", "Bearer " + jwt)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody : " + respBody);

        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.success").value(true));
        actions.andExpect(jsonPath("$.response[0].inquiryId").value(1));
        actions.andExpect(jsonPath("$.response[0].isReplied").value(true));
        actions.andExpect(jsonPath("$.response[0].title").value("상품 문의"));
        actions.andExpect(jsonPath("$.response[0].content").value("상품이 반팔도 셔츠도 입고 되면 좋겠는데  혹시 안 되나요?."));
        actions.andExpect(jsonPath("$.response[0].createdAt").value("2024-01-25 11:30"));
    }
}