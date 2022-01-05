package com.example.restaurant.controller;

import com.example.restaurant.domain.Reserve;
import com.example.restaurant.domain.ReserveRequestDto;
import com.example.restaurant.domain.Restaurant;
import com.example.restaurant.service.ReserveService;
import com.example.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class ReserveController {
    private final ReserveService reserveService;
    private final RestaurantService restaurantService;

    public String reserveForm(String restaurant_id, HttpServletRequest request){
        List<Restaurant> restaurantList  = restaurantService.getRestaurant(restaurant_id);
        request.setAttribute("restaurantList", restaurantList);

        return "/restaurant/restaurantReserve";
    }

    @PostMapping("/reserve")
    public Reserve reserveData(@RequestParam Map<String, String> formdata, HttpServletRequest request){
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("log");
        String restaurant_name = (String) formdata.get("restaurant_name");
        String restaurant_id = (String) formdata.get("restaurant_id");
        String phone = (String) formdata.get("phone");
        String reserve_time = (String) formdata.get("date");
        int cnt = Integer.parseInt(formdata.get("cnt"));
        String user_name = (String) formdata.get("name");

        ReserveRequestDto newDto = new ReserveRequestDto(user_id, restaurant_id, restaurant_name, phone, reserve_time, cnt, user_name);
        return reserveService.reserveData(newDto);
    }

    public String getMyReserve(String user_id, HttpServletRequest request){
        List<Reserve> reserveList  = reserveService.getMyReserve(user_id);
        request.setAttribute("reserveList", reserveList);

        System.out.println("예약 : " + reserveList.size());
        System.out.println("예약내용: " + reserveList.get(0).getRestaurant_name());
        return "/restaurant/myReserve";
    }

    // ---- 사장님 예약 확인용 -----
    public String ownerReserveCheck(HttpServletRequest request){
        String id = request.getParameter("restaurant_id");
        List<Reserve> reserve = reserveService.getMyReserveByRestaurantId(id);
        request.setAttribute("reserve", reserve);
        return "user/ownerReserve";
    }
}
