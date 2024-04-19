package com.example.finalproject.domain.user;

import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final HttpServletRequest request;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        String file = "resources/static/img_file/cloth.png";
        request.setAttribute("imgFilename", file);
        return "index";
    }


}
