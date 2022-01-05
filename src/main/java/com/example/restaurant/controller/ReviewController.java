package com.example.restaurant.controller;

import com.example.restaurant.domain.Review;
import com.example.restaurant.domain.ReviewRequestDto;
import com.example.restaurant.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService service;

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
//        int no = Integer.parseInt(request.getParameter("no"));
        Review review = service.getReview(no);
        request.setAttribute("review", review);
        return "review/reviewView"; // d이동
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
        return "review/reviewUpdate"; // d이동
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

    //My Review
    public String getMyReviews(HttpServletRequest request){
//        int no = Integer.parseInt(request.getParameter("no"));
        HttpSession session = request.getSession();
        String user_id = (String)session.getAttribute("log");

        List<Review> reviewList = service.getReviews();
        List<Review> result = new ArrayList<>();

        for(Review r : reviewList){
            if(r.getUser_id().equals(user_id)){
                result.add(r);
            }
        }
        request.setAttribute("review", result);

        return "user/userMyPage";
    }
}