package com.example.restaurant.controller;

import com.example.restaurant.domain.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    private final ReviewController reviewController;

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

    @PostMapping("/v1/users")
    public String addUser(@RequestBody UserRequestDto userRequestDto, HttpServletRequest request) {
            return userController.addUser(userRequestDto, request);
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

    @GetMapping("/userMyPage")
    public String mypage(){
        return "user/userMyPage";
    }


    //----Review----

//    @GetMapping("/")
//    public String reviewIndex() {
//        return "index";
//    }

    @GetMapping("/list")
    public String reviewList(HttpServletRequest request) {
        return reviewController.getReviews(request);
    }

    @GetMapping("/writePage")
    public String reviewWritePage(){
        return "review/reviewWrite";
    }
    @PostMapping("/write")
    public String reviewWrite(@RequestParam Map<String,String> formdata, HttpServletRequest request){
        return reviewController.addReview(formdata, request);
    }

    @GetMapping("/reviewView")
    public String reviewView(@RequestParam int no, HttpServletRequest request){
        return reviewController.getReview(no, request);
    }
    @GetMapping("/reviewDelete")
    public String reviewDelete(HttpServletRequest request){
        return reviewController.deleteReview(request);
    }
    @GetMapping("/reviewUpdatePage")
    public String reviewUpdatePage(HttpServletRequest request){
        return reviewController.reviewUpdatePage(request);
    }

    @PostMapping("/update")
    public String reviewUpdate(@RequestParam Map<String,String> formdata, HttpServletRequest request){
        return reviewController.reviewUpdate(formdata, request);
    }

    // 레스토랑 리스트 뿌려주며 입장
    @GetMapping("/ownerPage")
    public String ownerPage(HttpServletRequest request){
//        String user_id = request.getParameter("user_id");
        return controller.getRestaurantList(request);
    }

    // 레스토랑 검색하는 페이지(test)로 이동
    @GetMapping("/searchPage")
    public String ownerSearch(){ return "user/test"; }

    //---- 새로 추가됨 ----
    // 내 레스토랑 추가히기 / 상세페이지
    @GetMapping("/settingRestaurant")
    public String settingRestaurant(@RequestParam String restaurant_id, HttpServletRequest request){
        System.out.println("식당아이디: " + restaurant_id);
        System.out.println("요청 : " + request);

        return controller.settingRestaurantForm(restaurant_id, request);
    }

    // (test에서) 내 가게로 등록하기
    @GetMapping("/setMyRestaurant")
    public String setMyRestaurant(HttpServletRequest request){
//        String restaurant_id = request.getParameter("restaurant_id");
//        String id = request.getParameter("user_id");
//        System.out.println(restaurant_id + " : " + id);

        return controller.setMyRestaurant(request);
    }

    // 각 레스토랑 별 예약내역 보러 가기
    @GetMapping("/ownerReserveCheck")
    public String ownerReserveCheck(HttpServletRequest request){
        String restaurant_id = request.getParameter("restaurant_id");
        System.out.println("restaurant_id:"+restaurant_id);
        return reserveController.ownerReserveCheck(request);
    }

    // 레스토랑에서 사장 user_id 삭제
    @GetMapping("/deleteRestaurantByUserid")
    public String deleteRestaurantByUserid(HttpServletRequest request){
        return controller.deleteRestaurantByUserid(request);
    }

//    // 오너 페이지 안 내 가게 -> 지도 보기
//    @GetMapping("/ownerMapView")
//    public String ownerMapView(HttpServletRequest request){
//        return "user/ownerMapViewPage";
//    }
}
