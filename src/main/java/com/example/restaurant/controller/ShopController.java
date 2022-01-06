package com.example.restaurant.controller;

import com.example.restaurant.domain.*;
import com.example.restaurant.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Controller
public class ShopController {
    private final ShopRepository repo;
    private final ShopService service;

    @PostMapping("/shopSave")
    public boolean addUser(@RequestBody ShopRequestDto shopRequestDto, HttpServletRequest request) {
        return service.addShop(shopRequestDto, request);
    }
//    public String addShop(ShopRequestDto shopRequestDto, HttpServletRequest request) {
//        String user_id = request.getParameter("log");
//        user_id = "banana";
//        Shop shop = new Shop(shopRequestDto);
//        shop.updateUserId(user_id);
//
//        if(service.addShop(shopRequestDto)){ // 가게 id 중복 체크
//            System.out.println("성공.." + shop.getRestaurant_name());
//            repo.save(shop);
////            request.setAttribute("proc", "success");
//        }
//
//        else{
//            System.out.println("실패.."+ shop.getRestaurant_name());
////            request.setAttribute("proc", "fail");
//        }
//        service.getShops(user_id, request);
//        return "user/ownerMyPage";
//    }

    // 사장ID 로 가게 정보 불러와서 리스트로 들어감
    public String getShopList(HttpServletRequest request){
        service.getShops(request); // 리스트 받아오기
        return "user/ownerMyShopListPage";
    }


    // 가게에 사장 ID 업데이트
//    public String setMyShop(HttpServletRequest request){
//        String restaurant_id = request.getParameter("restaurant_id");
//        String user_id = request.getParameter("user_id");
//        service.updateOwner(restaurant_id,user_id);
//
//        service.getShops(user_id, request);
//        return "user/ownerMyPage";
//    }



    // 가게에서 사장 ID 삭제하기
    public String deleteShopByUserId(HttpServletRequest request){
        service.deleteShop(request);
        return "user/ownerMyPage";
    }

    // ------- 사장 페이지 관련 --------

    // 내 레스토랑 추가하기 / 레스토랑 id로 레스토랑 불러오기
    // 이게 안됨 그냥 일단 추가 ㄱㄱ
//    public String settingShopForm(String restaurant_id, HttpServletRequest request){
//        List<Shop> shopList  = service.getShops(restaurant_id);
//        request.setAttribute("shopList", shopList);
//
//        return "/user/ownerSettingRestaurant";
//    }
}