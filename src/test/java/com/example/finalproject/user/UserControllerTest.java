//package com.example.finalproject.user;
//
//import com.example.finalproject.domain.user.User;
//import com.example.finalproject.domain.user.UserService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(UserController.class)
//public class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UserService userService;
//
//    @Test
//    public void testUpdateUserSuccess() throws Exception {
//        User user = new User(1L, "John Doe", "john.doe@example.com");
//        User updatedUser = new User(1L, "John Smith", "john.smith@example.com");
//
//        Mockito.when(userService.updateUser(Mockito.eq(1L), Mockito.any(User.class))).thenReturn(updatedUser);
//
//        mockMvc.perform(MockMvcRequestBuilders.put("/users/1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(new ObjectMapper().writeValueAsString(updatedUser)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1L))
//                .andExpect(jsonPath("$.name").value("John Smith"))
//                .andExpect(jsonPath("$.email").value("john.smith@example.com"));
//    }
//
//    @Test
//    public void testUpdateUserNotFound() throws Exception {
//        User updatedUser = new User(1L, "John Smith", "john.smith@example.com");
//
//        Mockito.when(userService.updateUser(Mockito.eq(1L), Mockito.any(User.class))).thenReturn(null);
//
//        mockMvc.perform(MockMvcRequestBuilders.put("/users/1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(new ObjectMapper().writeValueAsString(updatedUser)))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void testUpdateUserInvalidInput() throws Exception {
//        User updatedUser = new User(1L, "", "john.smith@example.com");
//
//        mockMvc.perform(MockMvcRequestBuilders.put("/users/1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(new ObjectMapper().writeValueAsString(updatedUser)))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    public void testUpdateUserInvalidEmail() throws Exception {
//        User updatedUser = new User(1L, "John Smith", "invalid-email");
//
//        mockMvc.perform(MockMvcRequestBuilders.put("/users/1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(new ObjectMapper().writeValueAsString(updatedUser)))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    public void testUpdateUserNoContent() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.put("/users/1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{}"))
//                .andExpect(status().isBadRequest());
//    }
//}