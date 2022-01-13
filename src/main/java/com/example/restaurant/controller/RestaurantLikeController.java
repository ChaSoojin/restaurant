package com.example.restaurant.controller;


import com.example.restaurant.domain.RestaurantLike;
import com.example.restaurant.domain.RestaurantLikeRequestDto;
import com.example.restaurant.service.RestaurantLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RestController
@RequiredArgsConstructor
public class RestaurantLikeController {
    private final RestaurantLikeService service;

    //내가 찜한 레스토랑 목록 조회
    public String getLike(HttpServletRequest request){
        List<RestaurantLike> result = service.getLike(request);
        System.out.println(result.size());

        request.setAttribute("likelist", result);

        return "restaurant/myRestaurantLike";
    }

    //찜한 목록인지 체크
    @PostMapping("/checklikes")
    public Map<String, String>  checkMyLikes(@RequestBody RestaurantLikeRequestDto dto, HttpServletRequest request){
        Map<String, String> map = new HashMap<>();

        if(service.checkLike(dto, request)){
            System.out.println("찜한 목록 중 존재..");
            map.put("proc", "success");
        }
        else{
            System.out.println("찜 목록에 없음!");
            map.put("proc", "fail");
        }

        return map;
    }


    //식당 찜하기
    public String addLike(@RequestBody RestaurantLikeRequestDto dto, HttpServletRequest request){
        if(service.addLike(dto, request)){
            System.out.println("성공");
        }
        else{
            System.out.println("실패");
        }
        return "";
    }

    //식당 찜하기 취소
    public String cancelLike(String restaurant_id, HttpServletRequest request){
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("log");

        service.cancelLike(restaurant_id,request);
        getLike(request);

        return "restaurant/myRestaurantLike";
    }
}
