package com.example.finalproject.domain.items;

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
class ItemsRestControllerTest {

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
    public void GetCreatorView_success_test() throws Exception {
        // given
        int itemId = 1;

        // when
        ResultActions actions = mvc.perform(
                get("/app/item-detail-pages/" + itemId)
                        .header("Authorization", "Bearer " + jwt)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody : " + respBody);

        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.success").value(true));
        actions.andExpect(jsonPath("$.response.itemId").value(1));
        actions.andExpect(jsonPath("$.response.brandName").value("SALOMON"));
        actions.andExpect(jsonPath("$.response.itemName").value("SCRAPPED 티셔츠(WHITE)"));
        actions.andExpect(jsonPath("$.response.price").value(45000));
        actions.andExpect(jsonPath("$.response.discountPrice").value(0));
        actions.andExpect(jsonPath("$.response.finalPrice").value(45000));
        actions.andExpect(jsonPath("$.response.mainPhotos[0].photoId").value(30));
        actions.andExpect(jsonPath("$.response.mainPhotos[0].mainPhotoName").value("uuid_아이템사진1"));
        actions.andExpect(jsonPath("$.response.mainPhotos[0].photoPath").value("/upload/items/item01/mainItemPhoto.jpg"));
        actions.andExpect(jsonPath("$.response.mainPhotos[0].sort").value("ITEM"));
        actions.andExpect(jsonPath("$.response.detailPhotos[0].itemPhotoId").value(31));
        actions.andExpect(jsonPath("$.response.detailPhotos[0].subPhotoName").value("uuid_아이템사진1서브"));
        actions.andExpect(jsonPath("$.response.detailPhotos[0].photoPath").value("/upload/items/item01/detail01.jpg"));
        actions.andExpect(jsonPath("$.response.detailPhotos[0].isMainPhoto").value(false));
        actions.andExpect(jsonPath("$.response.detailPhotos[0].sort").value("ITEM"));
    }

    @Test
    public void GetCreatorView_fail_test() throws Exception {
        // given
        int itemId = 999;

        // when
        ResultActions actions = mvc.perform(
                get("/app/item-detail-pages/" + itemId)
                        .header("Authorization", "Bearer " + jwt)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody : " + respBody);

        // then
        actions.andExpect(jsonPath("$.status").value(404));
        actions.andExpect(jsonPath("$.success").value(false));
        actions.andExpect(jsonPath("$.response").doesNotExist());
        actions.andExpect(jsonPath("$.errorMessage").value("등록된 아이템이 아닙니다."));
    }

    @Test
    public void searchItems_success_test() throws Exception {
        // given
        String keyword = "";

        // when
        ResultActions actions = mvc.perform(
                get("/app/search-items")
                        .header("Authorization", "Bearer " + jwt)
                        .param("keyword", keyword)
        );

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody : " + respBody);

        // then
        actions.andExpect(status().isOk()); // HTTP 상태코드가 200인지 확인
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.success").value(true));
        actions.andExpect(jsonPath("$.response").isArray()); // response 배열인지 확인
        actions.andExpect(jsonPath("$.response[0].itemId").value(1));
        actions.andExpect(jsonPath("$.response[0].name").value("SCRAPPED 티셔츠(WHITE)"));
        actions.andExpect(jsonPath("$.response[0].description").value("힙하고 유니크한 반팔로 어느 코디에도 잘 어울립니다."));
        actions.andExpect(jsonPath("$.response[0].price").value(45000));
        actions.andExpect(jsonPath("$.response[0].itemPhotoId").value(30));
        actions.andExpect(jsonPath("$.response[0].itemPhotoName").value("uuid_아이템사진1"));
        actions.andExpect(jsonPath("$.response[0].photoPath").value("/upload/items/item01/mainItemPhoto.jpg"));
    }
}