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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class ReserveController {
    private final ReserveService reserveService;
    private final RestaurantService restaurantService;
    private final ReviewController reviewController;

    public String reserveForm(String restaurant_id, HttpServletRequest request){
        List<Restaurant> restaurantList  = restaurantService.getRestaurant(restaurant_id);
        request.setAttribute("restaurantList", restaurantList);

        return "/restaurant/restaurantReserve";
    }

    public String reserveData(@RequestParam Map<String, String> formdata, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("log");
        String restaurant_name = (String) formdata.get("restaurant_name");
        String restaurant_id = (String) formdata.get("restaurant_id");
        String phone = (String) formdata.get("phone");
        String reserve_time = (String) formdata.get("date");
        int cnt = Integer.parseInt(formdata.get("cnt"));
        String user_name = (String) formdata.get("name");

        ReserveRequestDto newDto = new ReserveRequestDto(user_id, restaurant_id, restaurant_name, phone, reserve_time, cnt, user_name);
        reserveService.reserveData(newDto);

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('예약이 완료되었습니다.');</script>");

        return "main";
    }

    public String getMyReserve(String user_id, HttpServletRequest request){
        List<Reserve> reserveList  = reserveService.getMyReserve(user_id);
        request.setAttribute("reserveList", reserveList);

        System.out.println("예약 : " + reserveList.size());
        System.out.println("예약내용: " + reserveList.get(0).getRestaurant_name());
        return "/restaurant/myReserve";
    }

    public String deleteReserve(@RequestParam int no, HttpServletRequest request){
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("log");

        reserveService.deleteReserve(no);
        reviewController.getMyDatas(request);

        return "user/userMyPage";
    }

    // ---- 사장님 예약 확인용 -----
    // 레스토랑 아이디로 예약내역 가져오기
    public String ownerReserveCheck(HttpServletRequest request){
        String id = request.getParameter("restaurant_id");
        List<Reserve> reserve = reserveService.getMyReserveByRestaurantId(id);
        request.setAttribute("reserve", reserve);
        return "user/ownerReserve";
    }

    // 예약내역 지우고 다시 예약내역 보러가기
    public String ownerDeleteReserve(HttpServletRequest request){
        int no = Integer.parseInt(request.getParameter("no"));
        String id = request.getParameter("restaurant_id");

        reserveService.deleteReserve(no);

        List<Reserve> reserve = reserveService.getMyReserveByRestaurantId(id);
        request.setAttribute("reserve", reserve);
        return "user/ownerReserve";
    }
}
