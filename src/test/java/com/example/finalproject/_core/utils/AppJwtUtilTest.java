package com.example.finalproject._core.utils;

import com.example.finalproject.domain.user.SessionUser;
import com.example.finalproject.domain.user.User;
import org.junit.jupiter.api.Test;

public class AppJwtUtilTest {

    @Test
    public void create_test() {

        User user = User.builder()
                .id(3)
                .myName("박선규")
                .email("user3@example.com")
                .blueChecked(true)
                .build();

        String jwt = AppJwtUtill.create(user);
        System.out.println("jwt : " + jwt);

        SessionUser sessionUser = AppJwtUtill.verify(jwt);

        System.out.println(sessionUser);
    }
}
