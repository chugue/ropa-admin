package com.example.finalproject.photo;

import com.example.finalproject._core.utils.AppJwtUtil;
import com.example.finalproject.domain.codi.CodiRequest;
import com.example.finalproject.domain.photo.Photo;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class PhotoControllerTest {
    @Autowired
    private MockMvc mvc;

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
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.popularItemsPhotos[0].photoId").value(30));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.popularItemsPhotos[0].name").value("SCRAPPED 티셔츠(WHITE)"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.popularItemsPhotos[0].sort").value("ITEM"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.popularItemsPhotos[0].photoPath").value("/upload/items/item01/mainItemPhoto.jpg"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.popularItemsPhotos[0].itemsId").value(1));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.popularItemsPhotos[0].adminInfo.brandId").value(1));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.popularItemsPhotos[0].adminInfo.brandName").value("SALOMON"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.popularCodiPhotos[0].photoId").value(14));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.popularCodiPhotos[0].codiId").value(1));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.popularCodiPhotos[0].name").value("uuid_코디사진1"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.popularCodiPhotos[0].photoPath").value("/upload/codi/user-3-codi1.webp"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.popularCodiPhotos[0].sort").value("CODI"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.popularCodiPhotos[0].isMainPhoto").value(true));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage").doesNotExist());
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
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.codiPhotos[0].photoId").value(14));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.codiPhotos[0].codiId").value(1));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.codiPhotos[0].photoPath").value("/upload/codi/user-3-codi1.webp"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.itemPhotos[0].itemsId").value(17));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.itemPhotos[0].itemName").value("다리가 길어보이는 투턱 세미 와이드 슬랙스 팬츠"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.itemPhotos[0].itemDescription").value("다리가 길어보이는 투턱 세미 와이드 팬츠로 어느룩에든 잘 어울립니다."));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.itemPhotos[0].itemPrice").value(75000));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.itemPhotos[0].photoId").value(65));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.response.itemPhotos[0].photoPath").value("/upload/items/item17/mainItemPhoto.jpg"));
        actions.andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage").doesNotExist());

    }

}