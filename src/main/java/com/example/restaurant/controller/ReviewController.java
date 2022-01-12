package com.example.restaurant.controller;

import com.example.restaurant.domain.Reserve;
import com.example.restaurant.domain.RestaurantLike;
import com.example.restaurant.domain.Review;
import com.example.restaurant.domain.ReviewRequestDto;
import com.example.restaurant.service.ReserveService;
import com.example.restaurant.service.RestaurantLikeService;
import com.example.restaurant.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReserveService reserveService;
    private final ReviewService service;
    private final UserController userController;
    private final RestaurantLikeService restaurantLikeService;

    // create
    public String addReview(@RequestParam Map<String,String> formdata, HttpServletRequest request){
        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("log");

        String title = (String) formdata.get("title");
        String content = (String) formdata.get("content");
        String restaurant_id = (String) formdata.get("restaurant_id");

        System.out.println(id + ", " + restaurant_id + ", " + title + ", " + content);
        ReviewRequestDto newDto = new ReviewRequestDto(id, restaurant_id, title, content);

        service.addReview(newDto);

        getReviews(request);
        return "/review/reviewList";
    }

    // Read
    public String getReview(int no, HttpServletRequest request){
        Review review = service.getReview(no);
        request.setAttribute("review", review);
        return "review/reviewView"; // 이동
    }

    public String getReviews(HttpServletRequest request){
        List<Review> list = service.getReviews();
        request.setAttribute("list", list);
        return "review/reviewList";
    }

    // Update
    public String reviewUpdatePage(HttpServletRequest request){
        int no = Integer.parseInt(request.getParameter("no"));
        getReview(no,request);  // get
        return "review/reviewUpdate"; // 이동
    }
    public String reviewUpdate(@RequestParam Map<String,String> formdata, HttpServletRequest request){
//        HttpSession session = request.getSession(); // session 의 아이디 가져오는것
        int no = Integer.parseInt(formdata.get("no"));
        String title = (String) formdata.get("title");
        String content = (String) formdata.get("content");

        System.out.println("no:" + no);
        System.out.println("title:" + title);
        System.out.println("content:" + content);

        ReviewRequestDto dto = new ReviewRequestDto(no, title, content);

        if(service.updateReview(no, dto)) {
            System.out.println("업데이트 성공!");
        }
        getReviews(request);
        return "/review/reviewList";
    }

    // Delete
    public String deleteReview(HttpServletRequest request){
        int no = Integer.parseInt(request.getParameter("no"));
        service.deleteReview(no);
        getReviews(request); // 리뷰리스트 받아오기
        return "review/reviewList";
    }

    //내 예약내역과 리뷰를 모두 마이페이지에서 조회
    public String getMyDatas(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String user_id = (String)session.getAttribute("log");

        List<Review> reviewList = service.getReviews();
        List<Review> result = new ArrayList<>();

        List<Reserve> reserveList = reserveService.getMyReserve(user_id);
        List<Reserve> result2 = new ArrayList<>();

        List<RestaurantLike> likeList = restaurantLikeService.getLike(request);
        List<RestaurantLike> result3 = new ArrayList<>();

        for(Review r : reviewList){
            if(r.getUser_id().equals(user_id)){
                result.add(r);
                System.out.println("ID: " + r.getUser_id() + " Title: " + r.getTitle());
            }
        }

        for(Reserve r2 : reserveList){
            result2.add(r2);
            System.out.println("ID: " + r2.getUser_id() + " --- 식당: " + r2.getRestaurant_name());
        }

        for(RestaurantLike r3 : likeList){
            result3.add(r3);
            System.out.println("ID: " + r3.getUser_id() + " --- 식당: " + r3.getRestaurant_name());
        }

        System.out.println("리스트: " + result.size());
        System.out.println("리스트2: " + result2.size());
        System.out.println("리스트3: " + result3.size());

        request.setAttribute("myreview", result);
        request.setAttribute("myreserve", result2);
        request.setAttribute("mylike", result3);

        userController.getUser(request);

        return "user/userMyPage";
    }

    // Like + -
    public boolean addLike(int no){
        return service.addLike(no);
    }
    public boolean minusLike(int no){
        return service.minusLike(no);
    }

    //----- 사장 리뷰 확인용 -----

    // 레스토랑 아이디로 리뷰내역 가져오기
    public String ownerReviewCheck(HttpServletRequest request){
        service.getMyReviewByRestaurantId(request);
        return "user/ownerReview";
    }

    // 상세 리뷰 가지고 오기 위해 하나의 리뷰 저장!
    public String ownerReviewView(HttpServletRequest request){
        service.getReviewWithoutView(request);
        return "user/ownerReviewViewPage";
    }

    // comment update
    public String ownerReviewUpdate(HttpServletRequest request){
        service.updateComment(request);
        service.getReviewWithoutView(request); // 원래페이지로 돌아가기 위한 리뷰 저장
        return "user/ownerReviewViewPage";
    }
}