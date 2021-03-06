package com.example.restaurant.controller;

import com.example.restaurant.domain.Restaurant;
import com.example.restaurant.domain.RestaurantLike;
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
import java.util.List;
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

        // ??????????????? ????????? ????????? ????????? ??????????????? login.jsp???? ?????????
        // ???????????? ????????? ?????? ??????log??? ??????????????? ????????? ?????? ???????????? ??????
        return "main";
    }

    //????????????????????? ????????????????????? ???????????? ???????????????????????? ???????????????????????? ?????????
//    @GetMapping("/userUpdatePage")
//    public String userUpdatePage(HttpServletRequest request){
//        return userController.getUser(request);
//
//    }

    // ??????????????????????????? ??????????????? db??????
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

    //?????? ?????? ?????????
    @GetMapping("/detail")
    public String getRestaurant(@RequestParam String restaurant_id, HttpServletRequest request){
        return controller.resDetail(restaurant_id,request);
    }

    //???????????? ??????????????? ???????????? ????????? ????????? ??????
    @GetMapping("/restaurantReserve")
    public String restaurantReserve(@RequestParam String restaurant_id, HttpServletRequest request){
        System.out.println("???????????????: " + restaurant_id);
        System.out.println("?????? : " + request);

        return reserveController.reserveForm(restaurant_id, request);
    }

    //???????????????
    @GetMapping("/userMyPage")
    public String mypage(HttpServletRequest request){
        return reviewController.getMyDatas(request);
    }

    //????????????
    @GetMapping("/reserveDelete")
    public String reserveDelete(@RequestParam int no, HttpServletRequest request){
        return reserveController.deleteReserve(no, request);
    }

    //?????? ????????????
    @PostMapping("/reserve")
    public String reserveData(@RequestParam Map<String, String> formdata, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return reserveController.reserveData(formdata, request, response);
    }

    //?????? ?????? ?????? ?????? ?????????
    @GetMapping("/restaurantLike")
    public String restaurantLike(HttpServletRequest request){
        HttpSession session = request.getSession();
        String user_id = (String)session.getAttribute("log");

        if(user_id == null){
            return "user/login";
        }

        List<RestaurantLike> likelist = restaurantLikeController.checkMyLikes(request);
        request.setAttribute("likelist", likelist);

        return "restaurant/restaurantLikeList";
    }

    //?????? ?????????
    @PostMapping("/like")
    public String addLike(@RequestBody RestaurantLikeRequestDto dto, HttpServletRequest request){
        Restaurant r = controller.addRestaurant(dto);

        request.setAttribute("restaurant", r);
        return restaurantLikeController.addLike(dto, request);
    }

    //?????? ?????? ?????? ??????
    @GetMapping("/myLike")
    public String myRestaurantLike(HttpServletRequest request){
        HttpSession session = request.getSession();
        String user_id = (String)session.getAttribute("log");


        if(user_id == null){
            return "user/login";
        }

        return restaurantLikeController.getLike(request);
    }

    //?????? ????????? ??????
    @GetMapping("/myLikeDelete")
    public String likeDelete(@RequestParam String restaurant_id, HttpServletRequest request){
        System.out.println("????????? ID: " + restaurant_id);
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

        // ?????? ???????????? ???????????? reviewLike ????????? ??????????????? ??? by user_id , review_no
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

    // ????????? ??????
    @GetMapping("/addLike")
    public String addLike(@RequestParam int no, HttpServletRequest request){
        // ????????? ??????
        reviewLikeController.addLike(request); // ??????????????? ?????????
        reviewController.addLike(no); // ????????? ?????? +1
        // ????????? ????????????
        reviewLikeController.existData(request); // likeData ????????????
        return reviewController.getReview(no, request); // ?????? ????????????
    }
    // ????????? ??????
    @GetMapping("/deleteLike")
    public String deleteLike(@RequestParam int no, HttpServletRequest request){
        // ????????? ??????
        reviewLikeController.deleteLike(request);// ??????????????? ??????
        reviewController.minusLike(no); // ????????? ?????? -1
        // ????????? ????????????
        reviewLikeController.existData(request); // likeData ????????????
        return reviewController.getReview(no, request); // reviewData ???????????? ??????
    }

    // ----- ??????????????? ----

    // ??????????????? ????????????
    @GetMapping("/ownerPage")
    public String ownerShopList(HttpServletRequest request){
        return shopController.getMyDatas(request);
    }

    // ???????????? ???????????? ???????????? ??????
    @GetMapping("/searchPage")
    public String ownerSearch(HttpServletRequest request){
        return "user/ownerSearch";
    }

    // ??? ???????????? ??? ???????????? ?????? ??????
    @GetMapping("/ownerReserveCheck")
    public String ownerReserveCheck(HttpServletRequest request){
        return reserveController.ownerReserveCheck(request);
    }

    // ??? ???????????? ?????? ????????? ?????? ??????
    @GetMapping("/ownerReviewCheck")
    public String ownerReviewCheck(HttpServletRequest request){
        return reviewController.ownerReviewCheck(request);
    }

    // ?????? ?????? ????????????
    @GetMapping("/ownerReviewView")
    public String ownerReviewView(HttpServletRequest request){
        return reviewController.ownerReviewView(request);
    }

    // ???????????? ????????? ?????? ???????????? ????????????
    @GetMapping("/deleteReserve")
    public String deleteReserve(HttpServletRequest request){
        return reserveController.deleteReserve(request);
    }

    // ???????????? ??????
    @GetMapping("/deleteRestaurantByUserId")
    public String deleteRestaurantByUserId(HttpServletRequest request){
        return shopController.deleteShopByUserId(request);
    }

    // ?????? ????????? ??? ??? ?????? -> ?????? ??????
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

    // ????????? ?????? / no, comment??? ????????? review ????????????
    @PostMapping("/ownerReviewUpdate")
    public String ownerReviewUpdate(HttpServletRequest request){
        return reviewController.ownerReviewUpdate(request);
    }

}
