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
    private final OrderHistoryRepository orderHistoryRepository;
    private final OrderHistoryService orderHistoryService;

    //매출 목록보기
    @GetMapping("/admin/orderHistory")
    public String orderHistoryForm(HttpServletRequest req){
        Admin sessionAdmin = (Admin) session.getAttribute("sessionUser");
        List<OrderHistory> orderHistoryList = orderHistoryService.adminOrderHistory();
        req.setAttribute("orderHistoryList" ,orderHistoryList);
        return "sales/admin-sales-manage";
    }
}
