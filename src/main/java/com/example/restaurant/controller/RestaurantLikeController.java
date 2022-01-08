package com.example.restaurant.controller;


import com.example.restaurant.domain.RestaurantLike;
import com.example.restaurant.domain.RestaurantLikeRequestDto;
import com.example.restaurant.service.RestaurantLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RestController
@RequiredArgsConstructor
public class RestaurantLikeController {
    private final RestaurantLikeService service;

    public String getLike(HttpServletRequest request){
        List<RestaurantLike> result = service.getLike(request);
        System.out.println(result.size());

        request.setAttribute("likelist", result);

        return "restaurant/myRestaurantLike";
    }

    public String addLike(@RequestBody RestaurantLikeRequestDto dto, HttpServletRequest request){
        if(service.addLike(dto, request)){
            System.out.println("성공");
        }
        else{
            System.out.println("실퍂");
        }
        return "";
    }

    public String cancelLike(String restaurant_id, HttpServletRequest request){
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("log");

        service.cancelLike(restaurant_id,request);
        getLike(request);

        return "restaurant/myRestaurantLike";
    }
}
