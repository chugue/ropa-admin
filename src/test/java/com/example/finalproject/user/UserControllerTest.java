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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class UserControllerTest {
    private ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mvc;


    //회원가입
    @Test
    public void join_test() throws Exception {
        // given
        UserRequest.JoinDTO reqDTO = new UserRequest.JoinDTO();
        reqDTO.setEmail("p4rk@naver.com");
        reqDTO.setMyName("p4rk");
        reqDTO.setNickName("cat");
        reqDTO.setPassword("1234");

        String reqBody = om.writeValueAsString(reqDTO);

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

    //아이디 중복체크
    @Test
    public void join_username_same_fail_test() throws Exception {
        // given
        UserRequest.JoinDTO reqDTO = new UserRequest.JoinDTO();
        reqDTO.setEmail("junghein@example.com");
        reqDTO.setMyName("dfd");
        reqDTO.setNickName("dfdfd");
        reqDTO.setPassword("1234");

        String reqBody = om.writeValueAsString(reqDTO);

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

    //회원가입 유효성검사
    @Test
    public void join_username_valid_fail_test() throws Exception {
        // given
        UserRequest.JoinDTO reqDTO = new UserRequest.JoinDTO();
        reqDTO.setEmail("");
        reqDTO.setMyName("dfd");
        reqDTO.setNickName("cat");
        reqDTO.setPassword("1234");

        String reqBody = om.writeValueAsString(reqDTO);

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

    //로그인
    @Test
    public void login_success_test() throws Exception {
        // given
        UserRequest.LoginDTO reqDTO = new UserRequest.LoginDTO();
        reqDTO.setEmail("junghein@example.com");
        reqDTO.setPassword("1234");

        String reqBody = om.writeValueAsString(reqDTO);

        // when
        ResultActions actions = mvc.perform(
                post("/user/login")
                        .content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        String jwt = actions.andReturn().getResponse().getHeader("Authorization");

        // then
        actions.andExpect(status().isOk()); // header 검증
        actions.andExpect(result -> result.getResponse().getHeader("Authorization").contains("Bearer " + jwt));


        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.success").value(true));
        actions.andExpect(jsonPath("$.response.email").value("junghein@example.com"));
    }

    //로그인 실패 테스트
    @Test
    public void login_fail_test() throws Exception {
        // given
        UserRequest.LoginDTO reqDTO = new UserRequest.LoginDTO();
        reqDTO.setEmail("junghein@example.com");
        reqDTO.setPassword("12345");

        String reqBody = om.writeValueAsString(reqDTO);

        // when
        ResultActions actions = mvc.perform(
                post("/user/login")
                        .content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        actions.andExpect(status().isUnauthorized()); // header 검증
        actions.andExpect(jsonPath("$.status").value(401));
        actions.andExpect(jsonPath("$.errorMessage").value("사용자 정보를 찾을 수 없습니다."));
        actions.andExpect(jsonPath("$.response").isEmpty());
    }

    //로그인 유효성검사
    @Test
    public void login_username_valid_fail_test() throws Exception {
        // given
        UserRequest.LoginDTO reqDTO = new UserRequest.LoginDTO();
        reqDTO.setEmail("");
        reqDTO.setPassword("1234");

        String reqBody = om.writeValueAsString(reqDTO);

        // when
        ResultActions actions = mvc.perform(
                post("/user/login")
                        .content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();

        // then
        actions.andExpect(jsonPath("$.status").value(400));
        actions.andExpect(jsonPath("$.errorMessage").value("이메일이 공백일 수 없습니다 : email"));
    }





}