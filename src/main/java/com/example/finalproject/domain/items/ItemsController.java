package com.example.finalproject.domain.items;

import com.example.finalproject.domain.admin.Admin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ItemsController {
    private final HttpSession session;
    private final ItemsService itemsService;

    // 상품 관리 페이지
    @GetMapping("/api/items-manage")
    public String itemsManage(HttpServletRequest requestDTO) {
        Admin sessionBrand = (Admin) session.getAttribute("sessionBrand");
        List<ItemsResponse.listDTO> itemsList = itemsService.findItemsByAdminId(sessionBrand);
        requestDTO.setAttribute("itemsList", itemsList);
        return "items/items-manage";
    }

    // 상품 상세 페이지
    @GetMapping("/api/items-detail/{itemId}")
    public String itemsDetail(@PathVariable(name = "itemId") Integer itemId, HttpServletRequest requestDTO) {
        Admin sessionBrand = (Admin) session.getAttribute("sessionBrand");
        ItemsResponse.DetailDTO itemsDetail = itemsService.findItemsByAdminIdAndItemId(sessionBrand, itemId);
        requestDTO.setAttribute("itemsDetail", itemsDetail);
        return "items/items-detail";
    }

    // 아이템 등록 폼
    @GetMapping("/api/items-register-form")
    public String itemsRegisterForm() {
        return "items/items-register-form";
    }

    // 아이템 등록
    @PostMapping("/api/items-register")
    public String itemsRegister(@RequestParam("mainCategory") String mainCategory,
                                @RequestParam("subCategory") String subCategory,
                                ItemsRequest.SaveDTO requestDTO) {
        Admin sessionBrand = (Admin) session.getAttribute("sessionBrand");
        requestDTO.setMainCategory(mainCategory);
        requestDTO.setSubCategory(subCategory);
        // 카테고리 리스트 설정
        itemsService.saveItem(requestDTO, sessionBrand);
        return "redirect:/api/items-manage";
    }

    // 상품 수정 폼
    @GetMapping("/api/items-update-form")
    public String itemsUpdateForm() {
        return "items/items-update-form";
    }

}
