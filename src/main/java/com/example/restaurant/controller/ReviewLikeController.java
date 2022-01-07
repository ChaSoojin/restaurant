package com.example.restaurant.controller;

import com.example.restaurant.service.ReviewLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RestController
@RequiredArgsConstructor
public class ReviewLikeController {

    private final ReviewLikeService service;

    public boolean existData(HttpServletRequest request){
        return service.existData(request);
    }

    public String addLike(HttpServletRequest request){
        service.addData(request);
        return "review/reviewView";
    }

    public String deleteLike(HttpServletRequest request){
        service.deleteData(request);
        return "review/reviewView";
    }
}
