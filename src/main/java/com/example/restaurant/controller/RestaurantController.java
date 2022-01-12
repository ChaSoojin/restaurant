package com.example.restaurant.controller;

import com.example.restaurant.domain.Restaurant;
import com.example.restaurant.domain.RestaurantLikeRequestDto;
import com.example.restaurant.domain.RestaurantRequestDto;
import com.example.restaurant.domain.Review;
import com.example.restaurant.service.RestaurantService;
import com.example.restaurant.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class RestaurantController {
    private final RestaurantService service;
    private final ReviewService reviewService;

    //맛집검색 <- map.js
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

    public Restaurant addRestaurant(@RequestBody RestaurantLikeRequestDto dto){

        String restaurant_id = dto.getRestaurant_id();
        String restaurant_name = dto.getRestaurant_name();
        String phone = dto.getPhone();
        String x = dto.getX();
        String y = dto.getY();
        String address = dto.getAddress();
        String user_id = "";

        RestaurantRequestDto newDto = new RestaurantRequestDto(restaurant_id, user_id, restaurant_name, phone, address, x, y);
        return service.addRestaurant(newDto);
    }

    //맛집 상세보기
    public String resDetail(String restaurant_id, HttpServletRequest request){
        System.out.println("ID: " + restaurant_id);

        //1. Restaurant_id에 해당하는 맛집 데이터
        List<Restaurant> restaurantList  = service.getRestaurant(restaurant_id);

        //2. Restaurant_id에 해당하는 리뷰
        List<Review> reviewList = reviewService.getReviewByRestaurantId(request);

        request.setAttribute("restaurant", restaurantList);
        request.setAttribute("reviewList", reviewList);

        return "restaurant/restaurantDetail";
    }
}
