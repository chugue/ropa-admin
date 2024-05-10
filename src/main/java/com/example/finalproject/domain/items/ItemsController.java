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

    // 아이템 관리 페이지
    @GetMapping("/api/items-manage")
    public String itemsManage(@RequestParam(defaultValue = "itemCode") String searchBy, @RequestParam(defaultValue = "") String keyword, HttpServletRequest requestDTO) {
        Admin sessionBrand = (Admin) session.getAttribute("sessionBrand");
        List<ItemsResponse.list> itemsList = itemsService.findItemsByAdminId(sessionBrand.getId(), searchBy, keyword);
        requestDTO.setAttribute("itemsList", itemsList);
        return "items/items-manage";
    }

    // 아이템 상세 페이지
    @GetMapping("/api/items-detail/{itemId}")
    public String itemsDetail(@PathVariable(name = "itemId") Integer itemId, HttpServletRequest requestDTO) {
        Admin sessionBrand = (Admin) session.getAttribute("sessionBrand");
        ItemsResponse.Detail itemsDetail = itemsService.findItemsByAdminIdAndItemId(sessionBrand.getId(), itemId);
        requestDTO.setAttribute("itemsDetail", itemsDetail);
        return "items/items-detail";
    }

    // 아이템 등록 폼
    @GetMapping("/api/items-register-form")
    public String itemsRegisterForm() {
        return "items/items-register-form";
    }

    // 아이템 등록
    @PostMapping("/api/register/items")
    public String itemsRegister(@RequestParam("mainCategory") String mainCategory,
                                @RequestParam("subCategory") String subCategory,
                                ItemsRequest.SaveDTO reqDTO) {
        Admin sessionBrand = (Admin) session.getAttribute("sessionBrand");
        reqDTO.setMainCategory(mainCategory);
        reqDTO.setSubCategory(subCategory);
        // 카테고리 리스트 설정
        itemsService.saveItem(reqDTO, sessionBrand.getId());
        return "redirect:/api/items-manage";
    }

    // 아이템 수정 폼
    @GetMapping("/api/items-update-form/{itemId}")
    public String itemsUpdateForm(@PathVariable(name = "itemId") Integer itemId, HttpServletRequest requestDTO) {
        Admin sessionBrand = (Admin) session.getAttribute("sessionBrand");
        ItemsResponse.Detail itemsDetail = itemsService.findItemsByAdminIdAndItemId(sessionBrand.getId(), itemId);
        requestDTO.setAttribute("itemsDetail", itemsDetail);

        System.out.println("메인이미지에 무엇이 저장되어 있나요? -> " + itemsDetail.getItemMainImage());
        return "items/items-update-form";
    }

    // 아이템 수정
    @PostMapping("/api/register/items-update/{itemId}")
    public String itemsUpdate(@PathVariable("itemId") Integer itemId, ItemsRequest.UpdateDTO updateDTO) {
        Admin sessionBrand = (Admin) session.getAttribute("sessionBrand");
        itemsService.updateItem(itemId, updateDTO, sessionBrand.getId());
        return "redirect:/api/items-manage";
    }

    // 아이템 삭제
    @PostMapping("/api/items-delete/{itemId}")
    public String itemsDelete(@PathVariable("itemId") Integer itemId) {
        Admin sessionBrand = (Admin) session.getAttribute("sessionBrand");
        itemsService.deleteItem(itemId, sessionBrand);
        return "redirect:/api/items-manage";
    }
}