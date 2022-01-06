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
}
