package com.example.finalproject.domain.items;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ItemsRestController {
    private final HttpSession session;

    //아이템 상세 페이지
//    @GetMapping("/app/item-detail-pages/{itemId}")
//    public ResponseEntity<?> creatorView(@PathVariable int itemId) {
//        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
//        return ResponseEntity.ok(new ApiUtil<>());
//    }
}
