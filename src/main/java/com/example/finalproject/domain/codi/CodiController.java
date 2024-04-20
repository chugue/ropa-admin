package com.example.finalproject.domain.codi;

import com.example.finalproject._core.util.ApiUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class CodiController {
    private final HttpServletRequest request;

    // 코디 등록 (상의와 하의가 같이 저장됨) - 작업중 //TODO
    @PostMapping("/api/codi-register")
    public void codiRegister(
            @RequestParam(name = "top") String topItem,
            @RequestParam(name = "bottom") String bottomItem){

        System.out.println(topItem);
        System.out.println(bottomItem);
    };


    // 코디 등록 페이지 카테고리 검색 - main만 주어졌을때 //TODO
    @GetMapping("/api/codi-items/main")
    public ResponseEntity<?> findCodiItems(
            @RequestParam(name = "mainId") Integer mainId){

        //main과 sub값으로 아래와 같은 쿼리를 DB에 요청하면 됨
        /*
        select i from Item i
        join i.categories c
        join CategoryMap cm on c.id = cm.descendant
        where cm.ancestor = :mainId
        */
        System.out.println(mainId);
        List<Integer> numberList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            numberList.add(i);
        }
        return ResponseEntity.ok(new ApiUtil<>(numberList));
    }

    // 코디 등록 페이지 카테고리 검색 - main과 sub가 주어졌을때 //TODO
    @GetMapping("/api/codi-items/main-sub")
    public ResponseEntity<?> findCodiItemsMainSub(
            @RequestParam(name = "mainId") Integer mainId,
            @RequestParam(name = "subId") Integer subId){

        //main과 sub값으로 아래와 같은 쿼리를 DB에 요청하면 됨
        /*
         select i from Item i
        join i.categories c
        join CategoryMap cm on c.id = cm.descendant
        where cm.ancestor = :subId
        and :mainId in (
                select cm.ancestor from CategoryMap cm
        where cm.descendant = :subId)
        */

        System.out.println(mainId);
        System.out.println(subId);
        List<Integer> numberList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            numberList.add(i);
        }
        return ResponseEntity.ok(new ApiUtil<>(numberList));
    }
}
