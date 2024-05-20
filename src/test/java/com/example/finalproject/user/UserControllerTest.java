package com.example.finalproject.user;


import com.example.finalproject._core.utils.AppJwtUtil;
import com.example.finalproject.domain.user.User;
import com.example.finalproject.domain.user.UserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;




@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class UserControllerTest {
    private ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mvc;
    private static String jwt;

    @BeforeAll
    public static void setUp() {
        jwt = AppJwtUtil.create(
                User.builder()
                        .id(3)
                        .myName("변우석")
                        .email("bunwuseok@example.com")
                        .blueChecked(true)
                        .build());
    }

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

    //앱 세팅 테스트 (사용자 변경)
    @Test
    public void setting_success_test() throws Exception {
        // given

        // when
        ResultActions actions = mvc.perform(
                get("/app/setting")
                        .header("Authorization", "Bearer " + jwt)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
//        System.out.println("respBody:"+respBody);

        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.success").value(true));
        actions.andExpect(jsonPath("$.response.id").value(1));
        actions.andExpect(jsonPath("$.response.myName").value("정해인"));
        actions.andExpect(jsonPath("$.response.email").value("junghein@example.com"));
        actions.andExpect(jsonPath("$.response.nickName").value("junghein"));
        actions.andExpect(jsonPath("$.response.mobile").value("010-1234-5678"));
    }

    @Test
    public void setting_fail_test() throws Exception {
        // given

        // when
        ResultActions actions = mvc.perform(
                get("/app/setting")
                        .header("Authorization", "Bearer " + jwt)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
//        System.out.println("respBody: " + respBody);

        // then
        actions.andExpect(jsonPath("$.status").value(401));
        actions.andExpect(jsonPath("$.success").value(false));
        actions.andExpect(jsonPath("$.errorMessage").value("인증되지 않았습니다."));

    }

    //앱 프로필 테스트
    @Test
    public void profile_success_test() throws Exception {
        // given

        // when
        ResultActions actions = mvc.perform(
                get("/app/profile")
                        .header("Authorization", "Bearer " + jwt)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
//        System.out.println(respBody);

        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.success").value(true));
        actions.andExpect(jsonPath("$.response.userId").value(1));
        actions.andExpect(jsonPath("$.response.myName").value("정해인"));
        actions.andExpect(jsonPath("$.response.email").value("junghein@example.com"));
        actions.andExpect(jsonPath("$.response.nickName").value("junghein"));
        actions.andExpect(jsonPath("$.response.mobile").value("010-1234-5678"));
        actions.andExpect(jsonPath("$.response.photoDTO.id").value(4));
        actions.andExpect(jsonPath("$.response.photoDTO.name").value("uuid_사용자사진1"));
        actions.andExpect(jsonPath("$.response.photoDTO.photoPath").value("/upload/user/user1.webp"));
    }


    @Test
    public void profile_fail_test() throws Exception {
        // given

        // when
        ResultActions actions = mvc.perform(
                get("/app/profile")
                        .header("Authorization", "Bearer " + jwt)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();

        // then
        actions.andExpect(jsonPath("$.status").value(401));
        actions.andExpect(jsonPath("$.success").value(false));
        actions.andExpect(jsonPath("$.errorMessage").value("인증되지 않았습니다."));
    }

    // 크리에이터 지원 페이지
    @Test
    public void creator_apply_form_success_test() throws Exception {
        // given

        // when
        ResultActions actions = mvc.perform(
                get("/app/creator-apply-form")
                        .header("Authorization", "Bearer " + jwt)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
//        System.out.println(respBody);

        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.success").value(true));
        actions.andExpect(jsonPath("$.response.name").value("정해인"));
        actions.andExpect(jsonPath("$.response.instagram").value("holyhaein"));
        actions.andExpect(jsonPath("$.response.blueChecked").value("false"));
        actions.andExpect(jsonPath("$.response.status").value("신청 전"));
    }

    @Test
    public void creator_apply_form_fail_test() throws Exception {
        // given

        // when
        ResultActions actions = mvc.perform(
                get("/app/creator-apply-form")
                        .header("Authorization", "Bearer " + jwt)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();

        // then
        actions.andExpect(jsonPath("$.status").value(401));
        actions.andExpect(jsonPath("$.success").value(false));
        actions.andExpect(jsonPath("$.errorMessage").value("인증되지 않았습니다."));
    }

    //사용자 크리에이터 지원하기
    @Test
    public void creator_apply_success_test() throws Exception {
        // given
        UserRequest.CreatorApplyDTO reqDTO = new UserRequest.CreatorApplyDTO();
        reqDTO.setHeight("182");
        reqDTO.setComment("키 182 연예인 체형입니다.");
        reqDTO.setInstagram("abc@naver.com");
        reqDTO.setJob("학생");
        reqDTO.setWeight("72");

        String reqBody = om.writeValueAsString(reqDTO);

        // when
        ResultActions actions = mvc.perform(
                put("/app/creator-apply")
                        .header("Authorization", "Bearer " + jwt)
                        .content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
//        System.out.println(respBody);
        String respJwt = actions.andReturn().getResponse().getHeader("Authorization");

        // then
        actions.andExpect(status().isOk()); // 상태 코드 검증
        if (respJwt != null) {
            assertTrue(respJwt.contains("Bearer "));
        }

        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.success").value(true));
        actions.andExpect(jsonPath("$.id").value(1));
        actions.andExpect(jsonPath("$.name").value("정해인"));
        actions.andExpect(jsonPath("$.instagram").value("abc@naver.com"));
        actions.andExpect(jsonPath("$.response.blueChecked").value(false));
        actions.andExpect(jsonPath("$.response.status").value("승인 대기"));
    }

    @Test
    public void creator_apply_fail_test() throws Exception {
        // given
        UserRequest.CreatorApplyDTO reqDTO = new UserRequest.CreatorApplyDTO();
        reqDTO.setHeight("");
        reqDTO.setComment("키 182 연예인 체형입니다.");
        reqDTO.setInstagram("abc@naver.com");
        reqDTO.setJob("학생");
        reqDTO.setWeight("72");

        String reqBody = om.writeValueAsString(reqDTO);

        // when
        ResultActions actions = mvc.perform(
                put("/app/creator-apply")
                        .header("Authorization", "Bearer " + jwt)
                        .content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(respBody);
        String respJwt = actions.andReturn().getResponse().getHeader("Authorization");

        // then
        if (respJwt != null) {
            assertTrue(respJwt.contains("Bearer "));
        }

        actions.andExpect(jsonPath("$.status").value(400));
        actions.andExpect(jsonPath("$.success").value(false));
        actions.andExpect(jsonPath("$.errorMessage").value("키는 공백일 수 없습니다 : height"));
    }

    //크리에이터 뷰 페이지
    @Test
    public void creator_view_success_test() throws Exception {
        // given
        Integer userId = 3;

        // when
        ResultActions actions = mvc.perform(
                get("/app/creator-view/" + userId)
                        .header("Authorization", "Bearer " + jwt)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(respBody);


        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.success").value(true));
        actions.andExpect(jsonPath("$.response.userDTO.creatorId").value(3));
        actions.andExpect(jsonPath("$.response.userDTO.blueChecked").value(true));
        actions.andExpect(jsonPath("$.response.userDTO.photoName").value("uuid_사용자사진3"));
        actions.andExpect(jsonPath("$.response.userDTO.photoPath").value("/upload/user/user3.webp"));
        actions.andExpect(jsonPath("$.response.userDTO.nickName").value("bunwuseok"));
        actions.andExpect(jsonPath("$.response.userDTO.height").value("180cm"));
        actions.andExpect(jsonPath("$.response.userDTO.weight").value("75kg"));
        actions.andExpect(jsonPath("$.response.userDTO.job").value("직장인"));
        actions.andExpect(jsonPath("$.response.userDTO.introMsg").value("연예인 체형"));
        actions.andExpect(jsonPath("$.response.codiList[0].codiId").value(1));// Json데이터가 배열이면 몇번지의 데이터인지 기입해줘야 함.
        actions.andExpect(jsonPath("$.response.codiList[0].codiPhotoId").value(14));
        actions.andExpect(jsonPath("$.response.codiList[0].photoName").value("uuid_코디사진1"));
        actions.andExpect(jsonPath("$.response.codiList[0].photoPath").value("/upload/codi/user-3-codi1.webp"));
        actions.andExpect(jsonPath("$.response.codiList[0].codiPhoto").value("CODI"));
    }

    @Test
    public void creator_view_fail_test() throws Exception {
        // given
        Integer userId = 999;

        // when
        ResultActions actions = mvc.perform(
                get("/app/creator-view/" + userId)
                        .header("Authorization", "Bearer " + jwt)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody: " + respBody);

        // then
        actions.andExpect(jsonPath("$.status").value(401));
        actions.andExpect(jsonPath("$.success").value(false));
        actions.andExpect(jsonPath("$.errorMessage").value("크리에이터가 아닙니다."));
    }

    //유저 마이페이지
    @Test
    public void user_my_page_success_test() throws Exception {
        // given

        // when
        ResultActions actions = mvc.perform(
                get("/app/user-my-page")
                        .header("Authorization", "Bearer " + jwt)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(respBody);



        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.success").value(true));
        actions.andExpect(jsonPath("$.response.userId").value(3));
        actions.andExpect(jsonPath("$.response.photoName").value("uuid_사용자사진3"));
        actions.andExpect(jsonPath("$.response.photoPath").value("/upload/user/user3.webp"));
        actions.andExpect(jsonPath("$.response.nickName").value("bunwuseok"));
        actions.andExpect(jsonPath("$.response.orderCount").value(4));

    }

    @Test
    public void user_my_page_fail_test() throws Exception {
        // given

        // when
        ResultActions actions = mvc.perform(
                get("/app/user-my-page")
                        .header("Authorization", "Bearer " + jwt)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();


        // then
        actions.andExpect(jsonPath("$.status").value(401));
        actions.andExpect(jsonPath("$.success").value(false));
        actions.andExpect(jsonPath("$.errorMessage").value("인증되지 않았습니다."));

    }


    @Test
    public void search_all_multiple_results_success_test() throws Exception {
        // given


        // when
        ResultActions actions = mvc.perform(
                get("/app/search-all")
                        .header("Authorization", "Bearer " + jwt)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();

        // then
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.success").value(true));

        //첫번째 코디
        actions.andExpect(jsonPath("$.response.codiListDTOS").isArray());
        actions.andExpect(jsonPath("$.response.codiListDTOS[0].codiId").value(1));
        actions.andExpect(jsonPath("$.response.codiListDTOS[0].codiPhotoId").value(14));
        actions.andExpect(jsonPath("$.response.codiListDTOS[0].photoName").value("uuid_코디사진1"));
        actions.andExpect(jsonPath("$.response.codiListDTOS[0].photoPath").value("/upload/codi/user-3-codi1.webp"));


        // 첫번째 아이템
        actions.andExpect(jsonPath("$.response.codiListDTOS").isArray());
        actions.andExpect(jsonPath("$.response.itemListDTOS[0].itemId").value(1));
        actions.andExpect(jsonPath("$.response.itemListDTOS[0].name").value("SCRAPPED 티셔츠(WHITE)"));
        actions.andExpect(jsonPath("$.response.itemListDTOS[0].description").value("힙하고 유니크한 반팔로 어느 코디에도 잘 어울립니다."));
        actions.andExpect(jsonPath("$.response.itemListDTOS[0].price").value(45000));
        actions.andExpect(jsonPath("$.response.itemListDTOS[0].itemPhotoId").value(30));
        actions.andExpect(jsonPath("$.response.itemListDTOS[0].itemPhotoName").value("uuid_아이템사진1"));
        actions.andExpect(jsonPath("$.response.itemListDTOS[0].photoPath").value("/upload/items/item01/mainItemPhoto.jpg"));

    }

    @Test
    public void creator_my_page_success_test() throws Exception {
        // given


        // when
        ResultActions actions = mvc.perform(
                get("/app/creator-my-page")
                        .header("Authorization", "Bearer " + jwt)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody = " + respBody);

        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.success").value(true));
        actions.andExpect(jsonPath("$.response.userDTO.creatorId").value(3));
        actions.andExpect(jsonPath("$.response.userDTO.blueChecked").value(true));
        actions.andExpect(jsonPath("$.response.userDTO.photoName").value("uuid_사용자사진3"));
        actions.andExpect(jsonPath("$.response.userDTO.photoPath").value("/upload/user/user3.webp"));
        actions.andExpect(jsonPath("$.response.userDTO.nickName").value("bunwuseok"));
        actions.andExpect(jsonPath("$.response.userDTO.height").value("180cm"));
        actions.andExpect(jsonPath("$.response.userDTO.weight").value("75kg"));
        actions.andExpect(jsonPath("$.response.userDTO.job").value("직장인"));
        actions.andExpect(jsonPath("$.response.userDTO.introMsg").value("연예인 체형"));
        actions.andExpect(jsonPath("$.response.userDTO.orderCount").value(4));
        actions.andExpect(jsonPath("$.response.userDTO.mileage").value(3000));
        actions.andExpect(jsonPath("$.response.codiList[0].codiId").value(1));
        actions.andExpect(jsonPath("$.response.codiList[0].codiPhotoId").value(14));
        actions.andExpect(jsonPath("$.response.codiList[0].photoName").value("uuid_코디사진1"));
        actions.andExpect(jsonPath("$.response.codiList[0].photoPath").value("/upload/codi/user-3-codi1.webp"));
        actions.andExpect(jsonPath("$.response.codiList[0].codiPhoto").value("CODI"));
        actions.andExpect(jsonPath("$.response.itemList[0].itemId").value(5));
        actions.andExpect(jsonPath("$.response.itemList[0].name").value("crop cable sweater"));
        actions.andExpect(jsonPath("$.response.itemList[0].description").value("방모 원사임에도 모달이 섞여 기분좋은 찰랑거림이 있는게 매력적입니다."));
        actions.andExpect(jsonPath("$.response.itemList[0].price").value(75000));
        actions.andExpect(jsonPath("$.response.itemList[0].itemPhotoId").value(38));
        actions.andExpect(jsonPath("$.response.itemList[0].itemPhotoName").value("uuid_아이템사진5"));
        actions.andExpect(jsonPath("$.response.itemList[0].photoPath").value("/upload/items/item05/mainItemPhoto.jpg"));
        actions.andExpect(jsonPath("$.response.itemList[0].itemPhoto").value("ITEM"));

    }

    @Test
    public void creator_my_page_fail_test() throws Exception {
        // given


        // when
        ResultActions actions = mvc.perform(
                get("/app/creator-my-page")
                        .header("Authorization", "Bearer " + jwt)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
//        System.out.println("respBody = " + respBody);

        // then
        actions.andExpect(jsonPath("$.status").value(401));
        actions.andExpect(jsonPath("$.success").value(false));
        actions.andExpect(jsonPath("$.errorMessage").value("인증 되지 않았습니다."));
    }

    @Test
    public void user_profile_update_success_test() throws Exception {
        // given
        Integer userId = 3;

        UserRequest.ProfileUpdateDTO reqDTO = new UserRequest.ProfileUpdateDTO();
        reqDTO.setMyName("변우식");
        reqDTO.setNickName("bun");
        reqDTO.setPassword("12345");


        UserRequest.ProfileUpdateDTO.PhotoDTO photoDTO = new UserRequest.ProfileUpdateDTO.PhotoDTO();
        photoDTO.setName("uuid_사용자사진3");
        photoDTO.setBase64("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAADICAYAAACtWK6eAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAALEgAACxIB0t1+/AAAADl0RVh0U29mdHdhcmUAbWF0cGxvdGxpYiB2ZXJzaW9");
        reqDTO.setPhoto(photoDTO);

        String reqBody = om.writeValueAsString(reqDTO);

        // when
        ResultActions actions = mvc.perform(
                put("/user/profile/" + userId)
                        .header("Authorization", "Bearer " + jwt)
                        .content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
//        System.out.println(respBody);

        String respJwt = actions.andReturn().getResponse().getHeader("Authorization");
        // then
        actions.andExpect(status().isOk()); // 상태 코드 검증
        if (respJwt != null) {
            assertTrue(respJwt.contains("Bearer "));
        }

        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.success").value(true));
        actions.andExpect(jsonPath("$.response.userId").value(3));
        actions.andExpect(jsonPath("$.response.email").value("bunwuseok@example.com"));
        actions.andExpect(jsonPath("$.response.myName").value("변우식"));
        actions.andExpect(jsonPath("$.response.nickName").value("bun"));
        actions.andExpect(jsonPath("$.response.photo.photoId").value(6));
        actions.andExpect(jsonPath("$.response.photo.photoPath").value("/upload/user/3/98b27ce9-d3ac-4b59-9da8-4575243177d8_uuid_사용자사진3"));

    }

    @Test
    public void user_profile_update_fail_test() throws Exception {
        // given
        Integer userId = 3;

        UserRequest.ProfileUpdateDTO reqDTO = new UserRequest.ProfileUpdateDTO();
        reqDTO.setMyName(""); // Invalid myName
        reqDTO.setNickName("bun");
        reqDTO.setPassword("12345");

        UserRequest.ProfileUpdateDTO.PhotoDTO photoDTO = new UserRequest.ProfileUpdateDTO.PhotoDTO();
        photoDTO.setName("uuid_사용자사진3");
        photoDTO.setBase64("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAADICAYAAACtWK6eAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAALEgAACxIB0t1+/AAAADl0RVh0U29mdHdhcmUAbWF0cGxvdGxpYiB2ZXJzaW9");
        reqDTO.setPhoto(photoDTO);

        String reqBody = om.writeValueAsString(reqDTO);

        // when
        ResultActions actions = mvc.perform(
                put("/user/profile/" + userId)
                        .header("Authorization", "Bearer " + jwt)
                        .content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        actions.andExpect(status().isBadRequest()); // Status code validation
    }

    @Test
    public void user_profile_update_valid_fail_test() throws Exception {
        // given
        Integer userId = 3;

        UserRequest.ProfileUpdateDTO reqDTO = new UserRequest.ProfileUpdateDTO();
        reqDTO.setMyName("됐다됐다"); // Invalid myName
        reqDTO.setNickName("bun");
        reqDTO.setPassword("12");

        UserRequest.ProfileUpdateDTO.PhotoDTO photoDTO = new UserRequest.ProfileUpdateDTO.PhotoDTO();
        photoDTO.setName("uuid_사용자사진3");
        photoDTO.setBase64("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAADICAYAAACtWK6eAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAALEgAACxIB0t1+/AAAADl0RVh0U29mdHdhcmUAbWF0cGxvdGxpYiB2ZXJzaW9");
        reqDTO.setPhoto(photoDTO);

        String reqBody = om.writeValueAsString(reqDTO);

        // when
        ResultActions actions = mvc.perform(
                put("/user/profile/" + userId)
                        .header("Authorization", "Bearer " + jwt)
                        .content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        actions.andExpect(status().isBadRequest());
        actions.andExpect(jsonPath("$.status").value(400));
        actions.andExpect(jsonPath("$.success").value(false));
        actions.andExpect(jsonPath("$.errorMessage").value("비밀번호는 최소 4자 이상 최대 20자 이하여야 합니다. : password"));
    }

    //자동 로그인
    @Test
    public void app_auto_login_success_test() throws Exception {
        // given

        // when
        ResultActions actions = mvc.perform(
                post("/app/auto/login")
                        .header("Authorization", "Bearer " + jwt)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(respBody);

        String respJwt = actions.andReturn().getResponse().getHeader("Authorization");
        // then
        actions.andExpect(status().isOk()); // 상태 코드 검증
        if (respJwt != null) {
            assertTrue(respJwt.contains("Bearer "));
        }

        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.success").value(true));
        actions.andExpect(jsonPath("$.response.id").value(3));
        actions.andExpect(jsonPath("$.response.email").value("bunwuseok@example.com"));
        actions.andExpect(jsonPath("$.response.photo").value("/upload/user/user3.webp"));

    }

    @Test
    public void app_auto_login_fail_test() throws Exception {
        // given
        String invalidJwt = "";
        // when
        ResultActions actions = mvc.perform(
                post("/app/auto/login")
                        .header("Authorization", "Bearer " + invalidJwt)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println(respBody);

        String respJwt = actions.andReturn().getResponse().getHeader("Authorization");
        // then
//        actions.andExpect(status().isOk()); // 상태 코드 검증
//        if (respJwt != null) {
//            assertTrue(respJwt.contains("Bearer "));
//        }

        actions.andExpect(status().isUnauthorized()) // 상태 코드가 401(Unauthorized)인지 검증
                .andExpect(content().string(containsString("유효하지 않은 토큰입니다."))); // 응답 본문에 특정 메시지가

    }


}