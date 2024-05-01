package com.example.finalproject.domain.items;

import com.example.finalproject.domain.admin.Admin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ItemsController {
    private final HttpSession session;
    private final ItemsService itemsService;

    // 상품 관리 페이지
    @GetMapping("/api/items-manage")
    public String itemsManage(HttpServletRequest req) {
        Admin sessionAdmin = (Admin) session.getAttribute("sessionBrand");
//        List<ItemsResponse.ItemsListDTO> itemsList = itemsService.findItemsByAdminId(sessionAdmin.getId());
//

        return "items/items-manage";
    }

    // 상품 상세 페이지
    @GetMapping("/api/items-detail")
    public String itemsDetail() {
        return "items/items-detail";
    }

    // 상품 등록 폼
    @GetMapping("/api/items-register-form")
    public String itemsRegisterForm() {
        return "items/items-register-form";
    }

    // 상품 수정 폼
    @GetMapping("/api/items-update-form")
    public String itemsUpdateForm() {
        return "items/items-update-form";
    }

}
