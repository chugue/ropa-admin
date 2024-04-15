package com.example.finalproject;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final EntityManager em;

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
