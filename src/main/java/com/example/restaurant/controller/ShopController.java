package com.example.restaurant.controller;

import com.example.restaurant.domain.*;
import com.example.restaurant.service.ReserveService;
import com.example.restaurant.service.ReviewService;
import com.example.restaurant.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Controller
public class ShopController {
    private final ShopService service;
    private final ReviewService reviewService;
    private final ReserveService reserveService;
    private final UserController userController;

    // ownerMyPage로 들어가기 위해 정보 준비
    public String getMyDatas(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String user_id = (String)session.getAttribute("log");

        List<Review> reviewList = reviewService.getReviews();
        List<Review> result = new ArrayList<>();

        List<Reserve> reserveList = reserveService.getMyReserve(user_id);
        List<Reserve> result2 = new ArrayList<>();

        for(Review r : reviewList){
            if(r.getUser_id().equals(user_id)){
                result.add(r);
                System.out.println("ID: " + r.getUser_id() + " Title: " + r.getTitle());
            }
        }

        for(Reserve r2 : reserveList){
            result2.add(r2);
            System.out.println("ID: " + r2.getUser_id() + " --- 식당: " + r2.getRestaurant_name());
        }

        System.out.println("리스트: " + result.size());
        System.out.println("리스트2: " + result2.size());

        request.setAttribute("myreview", result);
        request.setAttribute("myreserve", result2);
        userController.getUser(request);
        service.getShops(request); // 리스트 받아오기
        return "user/ownerMyPage";
    }

    // 가게 삭제하기
    public String deleteShopByUserId(HttpServletRequest request){
        service.deleteShop(request);
        getMyDatas(request);
        return "user/ownerMyPage";
    }

    // 내 레스토랑 저장
    @PostMapping("/shopSave")
    public boolean addUser(@RequestBody ShopRequestDto shopRequestDto, HttpServletRequest request) {
        service.addShop(shopRequestDto, request);
        getMyDatas(request);
        return true;
    }

}