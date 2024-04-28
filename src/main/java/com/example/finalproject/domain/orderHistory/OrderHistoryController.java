package com.example.finalproject.domain.orderHistory;

import com.example.finalproject.domain.admin.Admin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class OrderHistoryController {

    private final HttpSession session;
    private final OrderHistoryService orderHistoryService;
}
