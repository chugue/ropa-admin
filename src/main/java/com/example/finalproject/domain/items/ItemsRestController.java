package com.example.finalproject.domain.items;

import com.example.finalproject._core.utils.ApiUtil;
import com.example.finalproject.domain.user.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ItemsRestController {
    private final HttpSession session;
    private final ItemsService itemsService;

    //아이템 상세 페이지
    @GetMapping("/app/item-detail-pages/{itemId}")
    public ResponseEntity<?> creatorView(@PathVariable int itemId) {
        ItemsResponse.ItemDetail respDTO = itemsService.itemDetail(itemId);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
}
