package com.example.restaurant.controller;

import com.example.restaurant.domain.RestaurantLikeRequestDto;
import com.example.restaurant.service.ReviewService;
import com.example.restaurant.service.UserService;
import lombok.RequiredArgsConstructor;
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
    private final ReviewService service;
    private final UserService userService;
    private final ShopController shopController;
    private final ReviewLikeController reviewLikeController;
    private final RestaurantLikeController restaurantLikeController;

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
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

    //클라이언트에서 업데이트페이지 가는중의 유저가입력한정보 업데이트하기위한 메소드
//    @GetMapping("/userUpdatePage")
//    public String userUpdatePage(HttpServletRequest request){
//        return userController.getUser(request);
//
//    }

    // 업데이트페이지에서 작성한정보 db저장
    @PostMapping("/userInfoUpdate")
    public String updateUser(@RequestParam Map<String, String> updateFormData, HttpServletRequest request){

        System.out.println("id");
        return userController.updateUser(updateFormData,request);
    }


    @PostMapping("/deleteUser")
    public String userDelete(@RequestParam Map<String, String> data, HttpServletRequest request){

        return userController.deleteUser(data,request);
    }



    //----Restaurant----
    @GetMapping("/restaurantSearch")
    public String resSearch(HttpServletRequest request){
        HttpSession session = request.getSession();
        String user_id = (String)session.getAttribute("log");

        if(user_id == null){
            return "user/login";
        }
        return "restaurant/restaurantSearch";
    }

    //맛집 상세 페이지
    @GetMapping("/detail")
    public String getRestaurant(@RequestParam String restaurant_id, HttpServletRequest request){
        return controller.resDetail(restaurant_id,request);
    }

    //맛집예약 페이지에서 출력해줄 필요한 데이터 구성
    @GetMapping("/restaurantReserve")
    public String restaurantReserve(@RequestParam String restaurant_id, HttpServletRequest request){
        System.out.println("식당아이디: " + restaurant_id);
        System.out.println("요청 : " + request);

        return reserveController.reserveForm(restaurant_id, request);
    }

    //마이페이지
    @GetMapping("/userMyPage")
    public String mypage(HttpServletRequest request){
        return reviewController.getMyDatas(request);
    }

    //예약취소
    @GetMapping("/reserveDelete")
    public String reserveDelete(@RequestParam int no, HttpServletRequest request){
        return reserveController.deleteReserve(no, request);
    }

    //식당 예약하기
    @PostMapping("/reserve")
    public String reserveData(@RequestParam Map<String, String> formdata, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return reserveController.reserveData(formdata, request, response);
    }

    @GetMapping("/restaurantLike")
    public String restaurantLike(HttpServletRequest request){
        HttpSession session = request.getSession();
        String user_id = (String)session.getAttribute("log");

        if(user_id == null){
            return "user/login";
        }

        return "restaurant/restaurantLikeList";
    }

    //식당 찜하기
    @PostMapping("/like")
    public String addLike(@RequestBody RestaurantLikeRequestDto dto, HttpServletRequest request){
        return restaurantLikeController.addLike(dto, request);
    }

    //내가 찜한 식당 목록
    @GetMapping("/myLike")
    public String myRestaurantLike(HttpServletRequest request){
        HttpSession session = request.getSession();
        String user_id = (String)session.getAttribute("log");

        if(user_id == null){
            return "user/login";
        }

        return restaurantLikeController.getLike(request);
    }

    //식당 찜하기 취소
    @GetMapping("/myLikeDelete")
    public String likeDelete(@RequestParam String restaurant_id, HttpServletRequest request){
        return restaurantLikeController.cancelLike(restaurant_id, request);
    }



    //----Review----

    @GetMapping("/list")
    public String reviewList(HttpServletRequest request) {
        return reviewController.getReviews(request);
    }

    @GetMapping("/writePage")
    public String reviewWritePage(@RequestParam String restaurant_id, HttpServletRequest request){
        request.setAttribute("restaurant_id", restaurant_id);

        return "review/reviewWrite";
    }
    @PostMapping("/write")
    public String reviewWrite(@RequestParam Map<String,String> formdata, HttpServletRequest request){
        return reviewController.addReview(formdata, request);
    }

    @GetMapping("/reviewView")
    public String reviewView(@RequestParam int no, HttpServletRequest request){

        // 리뷰 디테일로 넘어갈때 reviewLike 정보도 넘겨주어야 함 by user_id , review_no
        reviewLikeController.existData(request);
        // review data by review_no
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

    // 좋아요 추가
    @GetMapping("/addLike")
    public String addLike(@RequestParam int no, HttpServletRequest request){
        // 좋아요 수정
        reviewLikeController.addLike(request); // 테이블에서 더하고
        reviewController.addLike(no); // 좋아요 갯수 +1
        // 데이터 가져오기
        reviewLikeController.existData(request); // likeData 가져오고
        return reviewController.getReview(no, request); // 리뷰 가져오고
    }
    // 좋아요 삭제
    @GetMapping("/deleteLike")
    public String deleteLike(@RequestParam int no, HttpServletRequest request){
        // 좋아요 수정
        reviewLikeController.deleteLike(request);// 테이블에서 빼고
        reviewController.minusLike(no); // 좋아요 갯수 -1
        // 데이터 가져오기
        reviewLikeController.existData(request); // likeData 가져오고
        return reviewController.getReview(no, request); // reviewData 가져오고 이동
    }

    // ----- 사장페이지 ----

    // 사장페이지 들어가기
    @GetMapping("/ownerPage")
    public String ownerShopList(HttpServletRequest request){
        return shopController.getMyDatas(request);
    }

    // 레스토랑 검색하는 페이지로 이동
    @GetMapping("/searchPage")
    public String ownerSearch(HttpServletRequest request){
        return "user/ownerSearch";
    }

    // 각 레스토랑 별 예약내역 보러 가기
    @GetMapping("/ownerReserveCheck")
    public String ownerReserveCheck(HttpServletRequest request){
        return reserveController.ownerReserveCheck(request);
    }

    // 각 레스토랑 리뷰 리스트 보러 가기
    @GetMapping("/ownerReviewCheck")
    public String ownerReviewCheck(HttpServletRequest request){
        return reviewController.ownerReviewCheck(request);
    }

    // 상세 리뷰 보러가기
    @GetMapping("/ownerReviewView")
    public String ownerReviewView(HttpServletRequest request){
        return reviewController.ownerReviewView(request);
    }

    // 예약내역 지우고 다시 예약내역 보러가기
    @GetMapping("/deleteReserve")
    public String deleteReserve(HttpServletRequest request){
        return reserveController.deleteReserve(request);
    }

    // 레스토랑 삭제
    @GetMapping("/deleteRestaurantByUserId")
    public String deleteRestaurantByUserId(HttpServletRequest request){
        return shopController.deleteShopByUserId(request);
    }

    // 오너 페이지 안 내 가게 -> 지도 보기
    @GetMapping("/ownerMapView")
    public String ownerMapView(HttpServletRequest request){
        String x = request.getParameter("x");
        String y = request.getParameter("y");
        String name = request.getParameter("name");

        request.setAttribute("x", x);
        request.setAttribute("y", y);
        request.setAttribute("name", name);

        return "user/ownerMapViewPage";
    }

    // 사장님 댓글 / no, comment값 가지고 review 업데이트
    @PostMapping("/ownerReviewUpdate")
    public String ownerReviewUpdate(HttpServletRequest request){
        return reviewController.ownerReviewUpdate(request);
    }

}
