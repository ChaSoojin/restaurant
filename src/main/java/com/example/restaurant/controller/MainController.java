package com.example.restaurant.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class MainController {
    private final RestaurantController controller;
    private final ReserveController reserveController;
    private final UserController userController;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/main")
    public String main(){
        return "main";
    }
    @GetMapping("/join")
    public String join(){
        return "user/join";
    }
    @GetMapping("/login")
    public String login(){
        return "user/login";
    }

    @PostMapping("/loginProM")
    public String loginPro(@RequestParam Map<String, String> data, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return userController.checkLogin(data, request, response);
    }

    @GetMapping("/logoutPro")
    public String logout(HttpServletRequest request){
        HttpSession session= request.getSession();
        session.removeAttribute("log");

        // 로그아웃시 어디로 보낼지 상의후 수정해야함 login.jsp?로 보낼지
        // 메인으로 보낼지 일단 세션log값 없어지는거 확인을 위해 메인으로 보냄
        return "main";
    }


    //----Restaurant----
    @GetMapping("/restaurantSearch")
    public String resSearch(){
        return "restaurant/restaurantSearch";
    }

    @GetMapping("/detail")
    public String getRestaurant(@RequestParam String restaurant_id, HttpServletRequest request){
        return controller.resDetail(restaurant_id,request);
    }

    @GetMapping("/restaurantReserve")
    public String restaurantReserve(@RequestParam String restaurant_id, HttpServletRequest request){
        System.out.println("식당아이디: " + restaurant_id);
        System.out.println("요청 : " + request);

        return reserveController.reserveForm(restaurant_id, request);
    }
}
