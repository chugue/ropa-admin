package com.example.finalproject.domain;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class MainController {
    private final HttpServletRequest request;
    private final HttpSession session;

}
