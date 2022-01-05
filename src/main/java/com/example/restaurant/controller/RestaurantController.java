package com.example.restaurant.controller;

import com.example.restaurant.domain.Restaurant;
import com.example.restaurant.domain.RestaurantRequestDto;
import com.example.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class RestaurantController {
    private final RestaurantService service;

    @PostMapping("/restaurantSearch")
    public Restaurant addRestaurant(@RequestParam Map<String, String> data){

        String restaurant_id = (String) data.get("restaurant_id");
        String restaurant_name = (String) data.get("restaurant_name");
        String phone = (String) data.get("phone");
        String x = (String) data.get("x");
        String y = (String) data.get("y");
        String address = (String) data.get("address");
        String user_id = "";

        RestaurantRequestDto newDto = new RestaurantRequestDto(restaurant_id, user_id, restaurant_name, phone, address, x, y);
        return service.addRestaurant(newDto);
    }

    public String resDetail(String restaurant_id, HttpServletRequest request){
        System.out.println("ID: " + restaurant_id);
        List<Restaurant> restaurantList  = service.getRestaurant(restaurant_id);
        request.setAttribute("restaurant", restaurantList);

        return "restaurant/restaurantDetail";
    }

    // ------- 사장 페이지 관련 --------
    // 내 레스토랑 추가하기 / 레스토랑 id로 레스토랑 불러오기
    public String settingRestaurantForm(String restaurant_id, HttpServletRequest request){
        List<Restaurant> restaurantList  = service.getRestaurant(restaurant_id);
        request.setAttribute("restaurantList", restaurantList);

        return "/user/ownerSettingRestaurant";
    }

    // 가게에 사장 ID 업데이트
    public String setMyRestaurant(HttpServletRequest request){
        String restaurant_id = request.getParameter("restaurant_id");
        String user_id = request.getParameter("user_id");
        service.updateOwner(restaurant_id,user_id);

        service.getRestaurants(user_id, request);
        return "user/ownerMyPage";
    }

    // 사장ID 로 가게 정보 불러오기
    public String getRestaurantList(HttpServletRequest request){
        String user_id = request.getParameter("user_id");

        service.getRestaurants(user_id, request);
        return "user/ownerMyPage";
    }

    // 가게에서 사장 ID 삭제하기
    public String deleteRestaurantByUserid(HttpServletRequest request){
        String restaurant_id = request.getParameter("restaurant_id"); // 레스토랑에서 user_id 지우기 위해
        String user_id = request.getParameter("user_id"); // list 만들기 위해
        service.deleteOwner(restaurant_id);

        service.getRestaurants(user_id, request);
        return "user/ownerMyPage";
    }
}
