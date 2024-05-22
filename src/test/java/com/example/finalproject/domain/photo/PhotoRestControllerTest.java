package com.example.finalproject.domain.photo;

import com.example.finalproject.domain.MyRestDoc;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class PhotoRestControllerTest extends MyRestDoc {


    @Test
    public void getHomeLists_success_test() throws Exception {
        // when
        ResultActions actions = mvc.perform(MockMvcRequestBuilders.get("/home-lists"));
        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody = " + respBody);
        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.popularUserPhotos[0].photoId").value(6));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.popularUserPhotos[0].name").value("uuid_사용자사진3"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.popularUserPhotos[0].photoPath").value("/upload/user/user3.webp"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.popularUserPhotos[0].sort").value("USER"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.popularUserPhotos[0].creatorId").value(3));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.popularItemsPhotos[0].photoId").value(56));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.popularItemsPhotos[0].name").value("SCRAPPED 티셔츠(WHITE)"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.popularItemsPhotos[0].sort").value("ITEM"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.popularItemsPhotos[0].photoPath").value("/upload/items/item01/mainItemPhoto.jpg"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.popularItemsPhotos[0].itemsId").value(1));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.popularItemsPhotos[0].adminInfo.brandId").value(1));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.popularItemsPhotos[0].adminInfo.brandName").value("SALOMON"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.popularCodiPhotos[0].photoId").value(14));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.popularCodiPhotos[0].codiId").value(1));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.popularCodiPhotos[0].name").value("uuid_코디사진1"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.popularCodiPhotos[0].photoPath").value("/upload/codi/user-03-codi01.webp"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.popularCodiPhotos[0].sort").value("CODI"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.popularCodiPhotos[0].isMainPhoto").value(true));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage").doesNotExist());
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    @Test
    public void searchPage_success_test() throws Exception {
        // when
        ResultActions actions = mvc.perform(MockMvcRequestBuilders.get("/main-search-page"));
        // eye
        String respBody = actions.andReturn().getResponse().getContentAsString();
        System.out.println("respBody = " + respBody);
        // then
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.codiPhotos[0].photoId").value(97));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.codiPhotos[0].codiId").value(22));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.codiPhotos[0].photoPath").exists());
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.itemPhotos[0].itemsId").value(19));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.itemPhotos[0].itemName").value("[비침 X] 2TYPE 린넨 쿨링 밴딩 팬츠"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.itemPhotos[0].itemDescription").value("비침 없는 쿨링 린넨 팬츠, 여름 필수템!"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.itemPhotos[0].itemPrice").value(35000));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.itemPhotos[0].photoId").value(95));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.itemPhotos[0].photoPath").value("/upload/items/item19/mainItemPhoto.jpg"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage").doesNotExist());
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);

    }

}