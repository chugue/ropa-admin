package com.example.finalproject.user;


import com.example.finalproject.domain.user.UserRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class UserControllerTest {
    private ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

    @Test
    public void join_test() throws Exception {
        // given
        UserRequest.JoinDTO reqDTO = new UserRequest.JoinDTO();
        reqDTO.setEmail("p4rk@naver.com");
        reqDTO.setMyName("p4rk");
        reqDTO.setNickName("cat");
        reqDTO.setPassword("1234");

        String reqBody = om.writeValueAsString(reqDTO);
//        System.out.println("reqBody: " + reqBody);

        // when
        ResultActions actions = mvc.perform(
                post("/user/join")
                        .content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody: " + respBody);

        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.success").value(true));
        actions.andExpect(jsonPath("$.response.email").value("p4rk@naver.com"));
        actions.andExpect(jsonPath("$.response.nickName").value("cat"));
    }

    @Test
    public void join_username_same_fail_test() throws Exception {
        // given
        UserRequest.JoinDTO reqDTO = new UserRequest.JoinDTO();
        reqDTO.setEmail("junghein@example.com");
        reqDTO.setMyName("dfd");
        reqDTO.setNickName("dfdfd");
        reqDTO.setPassword("1234");

        String reqBody = om.writeValueAsString(reqDTO);
//        System.out.println("reqBody: " + reqBody);

        // when
        ResultActions actions = mvc.perform(
                post("/user/join")
                        .content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody: " + respBody);

        // then
        actions.andExpect(jsonPath("$.status").value(400));
        actions.andExpect(jsonPath("$.success").value(false));
        actions.andExpect(jsonPath("$.errorMessage").value("중복된 이메일이 있습니다."));
    }

    @Test
    public void join_username_valid_fail_test() throws Exception {
        // given
        UserRequest.JoinDTO reqDTO = new UserRequest.JoinDTO();
        reqDTO.setEmail("");
        reqDTO.setMyName("dfd");
        reqDTO.setNickName("cat");
        reqDTO.setPassword("1234");

        String reqBody = om.writeValueAsString(reqDTO);
//        System.out.println("reqBody: " + reqBody);

        // when
        ResultActions actions = mvc.perform(
                post("/user/join")
                        .content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody: " + respBody);

        // then
        actions.andExpect(jsonPath("$.status").value(400));
        actions.andExpect(jsonPath("$.errorMessage").value("이메일은 최소 1자 이상 최대 20자 이하여야 합니다. : email"));
    }
}