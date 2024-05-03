package com.example.finalproject.domain.codi;

import com.example.finalproject._core.utils.ApiUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class CodiRestController {
    private final HttpServletRequest request;
    private final CodiService codiService;

    // 앱] 크리에이터 보기 코디 목록 탭
    @GetMapping("/app/find-codies/{creatorId}")
    public void findCodies(){
//        codiService.findCreatorCodies();
    }


    // 코디 보기 페이지 (페이지내 아이템 목록 코디목록있음)
    @GetMapping("/app/codi-pages/{codiId}")
    public ResponseEntity<?> codiPage(@PathVariable Integer codiId){
        CodiResponse.MainViewDTO respDTO = codiService.codiPage(codiId);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }



    // 코디 등록 (상의와 하의가 같이 저장됨) - 웹에서 코디 등록 로직 사라짐..
    @PostMapping("/api/codi-register")
    public void codiRegister(
            @RequestParam(name = "top") String topItem,
            @RequestParam(name = "bottom") String bottomItem) {

        System.out.println(topItem);
        System.out.println(bottomItem);
    }



    // 코디 등록 페이지 카테고리 검색 - 웹에서 코디 등록 로직 사라짐..
    @GetMapping("/api/codi-items/main")
    public ResponseEntity<?> findCodiItemsByMain(
            @RequestParam(name = "mainId") Integer mainId) {

        //main과 sub값으로 아래와 같은 쿼리를 DB에 요청하면 됨
        /*
        select i from Item i
        join i.categories c
        join CategoryMap cm on c.id = cm.childId
        where cm.parentId = :mainId
        */
        System.out.println(mainId);
        List<Integer> numberList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            numberList.add(i);
        }
        return ResponseEntity.ok(new ApiUtil<>(numberList));
    }


    // 코디 등록 페이지 카테고리 검색 - 웹에서 코디등록 로직 사라짐..
    @GetMapping("/api/codi-items/main-sub")
    public ResponseEntity<?> findCodiItemsByMainSub(
            @RequestParam(name = "mainId") Integer mainId,
            @RequestParam(name = "subId") Integer subId) {

        //main과 sub값으로 아래와 같은 쿼리를 DB에 요청하면 됨
        /*
        select i from Item i
        join i.categories c
        join CategoryMap cm on c.id = cm.childId
        where cm.parentId = :subId
        and :mainId in (
                select cm.parentId from CategoryMap cm
        where cm.childId = :subId)
        */

        System.out.println(mainId);
        System.out.println(subId);

        //이건 테스트용 배열
        List<Integer> numberList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            numberList.add(i);
        }
        return ResponseEntity.ok(new ApiUtil<>(numberList));
    }
}
