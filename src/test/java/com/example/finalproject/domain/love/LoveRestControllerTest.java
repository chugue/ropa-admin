package com.example.finalproject.domain.love;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class LoveRestControllerTest {

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
                        .blueChecked(true)
                        .build());
    }

    //좋아요
    @Test
    public void love_save_success_test() throws Exception {

        // given
        int codiId = 1;

        // when
        ResultActions actions = mvc.perform(
                post("/app/function/love/" + codiId)
                        .header("Authorization", "Bearer " + jwt)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody : " + respBody);

        // then
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.success").value(true));
        actions.andExpect(jsonPath("$.response.userId").value(3));
        actions.andExpect(jsonPath("$.response.codiId").value(1));
        actions.andExpect(jsonPath("$.response.isLoved").value(true));
        actions.andExpect(jsonPath("$.response.loveCount").value(2));
        actions.andExpect(jsonPath("$.errorMessage").doesNotExist());
    }

    @Test
    public void love_save_fail_test() throws Exception {

        // given
        int codiId = 999;

        // when
        ResultActions actions = mvc.perform(
                post("/app/function/love/" + codiId)
                        .header("Authorization", "Bearer " + jwt)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody : " + respBody);

        // then
        actions.andExpect(status().isNotFound());
        actions.andExpect(jsonPath("$.status").value(404));
        actions.andExpect(jsonPath("$.success").value(false));
        actions.andExpect(jsonPath("$.response").doesNotExist());
        actions.andExpect(jsonPath("$.errorMessage").value("해당 게시물을 찾을 수 없습니다."));
    }

    //좋아요 삭제
    @Test
    public void love_delete_success_test() throws Exception {

        // given
        int codiId = 1;
        // when
        ResultActions actions = mvc.perform(
                delete("/app/function/love/" + codiId)
                        .header("Authorization", "Bearer " + jwt)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody : " + respBody);

        // then
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.success").value(true));
        actions.andExpect(jsonPath("$.response.userId").value(1));
        actions.andExpect(jsonPath("$.response.codiId").value(1));
        actions.andExpect(jsonPath("$.response.isLoved").value(false));
        actions.andExpect(jsonPath("$.response.loveCount").value(1));
        actions.andExpect(jsonPath("$.errorMessage").doesNotExist());
    }

    @Test
    public void love_delete_fail_test() throws Exception {

        // given
        int codiId = 999;
        // when
        ResultActions actions = mvc.perform(
                delete("/app/function/love/" + codiId)
                        .header("Authorization", "Bearer " + jwt)
                        .contentType(MediaType.APPLICATION_JSON)

        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody : " + respBody);

        // then
        actions.andExpect(status().isNotFound());
        actions.andExpect(jsonPath("$.status").value(404));
        actions.andExpect(jsonPath("$.success").value(false));
        actions.andExpect(jsonPath("$.response").doesNotExist());
        actions.andExpect(jsonPath("$.errorMessage").value("해당 게시물을 찾을 수 없습니다."));
    }
}
