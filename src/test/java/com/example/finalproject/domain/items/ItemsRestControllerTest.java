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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ItemsRestControllerTest {
    @Autowired
    private MockMvc mvc;
    private ObjectMapper om = new ObjectMapper();
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

    @Test
    public void searchItems_fail_test() throws Exception {
        // given
        String keyword = "청바지없어asdfasdfasdf요";
        // when
        ResultActions actions = mvc.perform(MockMvcRequestBuilders.get("/app/search-items")
                .param("keyword", keyword)
                .header("Authorization", "Bearer " + jwt));
        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody = " + respBody);
        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.success").value(false));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response").isEmpty());
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage").value("검색어는 10글자 이하로 입력해주세요."));

    }


    @Test
    public void searchItems_success_test() throws Exception {
        // given
        String keyword = "청바지";
        // when
        ResultActions actions = mvc.perform(MockMvcRequestBuilders.get("/app/search-items")
                .param("keyword", keyword)
                .header("Authorization", "Bearer " + jwt));
        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody = " + respBody);
        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response[0].itemId").value(2));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response[0].name").value("scratch 블루 청바지"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response[0].description").value("봄, 여름, 가을 까지 계절 상관없이 힙한 룩에 잘어울리는 청바지입니다."));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response[0].price").value(32000));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response[0].itemPhotoId").value(32));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response[0].itemPhotoName").exists());
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response[0].photoPath").value("/upload/items/item02/mainItemPhoto.jpg"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage").doesNotExist());
    }


    @Test
    public void creatorView_fail_test() throws Exception {
        // when
        ResultActions actions = mvc.perform(MockMvcRequestBuilders.get("/app/item-detail-pages/70")
                .header("Authorization", "Bearer " + jwt));
        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody = " + respBody);
        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(404));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.success").value(false));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response").isEmpty());
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage").value("등록된 아이템이 아닙니다."));
    }

    @Test
    public void creatorView_success_test() throws Exception {
        // when
        ResultActions actions = mvc.perform(MockMvcRequestBuilders.get("/app/item-detail-pages/1")
                .header("Authorization", "Bearer " + jwt));

        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody = " + respBody);
        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.itemId").value(1));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.brandName").value("SALOMON"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.itemName").value("SCRAPPED 티셔츠(WHITE)"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.price").value(45000));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.discountPrice").value(0));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.finalPrice").value(45000));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.mainPhotos[0].photoId").value(30));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.mainPhotos[0].mainPhotoName").exists());
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.mainPhotos[0].photoPath").value("/upload/items/item01/mainItemPhoto.jpg"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.mainPhotos[0].sort").value("ITEM"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.detailPhotos[0].itemPhotoId").value(31));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.detailPhotos[0].subPhotoName").exists());
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.detailPhotos[0].photoPath").value("/upload/items/item01/detail01.jpg"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.detailPhotos[0].isMainPhoto").value(false));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.detailPhotos[0].sort").value("ITEM"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage").doesNotExist());
    }
}
