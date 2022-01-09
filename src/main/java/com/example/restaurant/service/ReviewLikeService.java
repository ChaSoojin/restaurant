package com.example.restaurant.service;

import com.example.restaurant.domain.ReviewLike;
import com.example.restaurant.domain.ReviewLikeRepository;
import com.example.restaurant.domain.ReviewLikeRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewLikeService {

    private final ReviewLikeRepository repo;

    // 해당 데이터가 존재하는지 확인
    public boolean existData(HttpServletRequest request){
        HttpSession session = request.getSession();
        String user_id = (String)session.getAttribute("log");

        int review_no = Integer.parseInt(request.getParameter("no"));

        System.out.println("reviewLikeService / id: " + user_id + " review_no: " + review_no);

        boolean exist = false; // 존재하지 않는다고 가정
        List<ReviewLike> datas = repo.findAll();
        for(ReviewLike data : datas){
            if(data.getReview_no() == review_no && data.getUser_id().equals(user_id)){
                exist = true; // 존재함
            }
        }
        request.setAttribute("like_exist", exist); // true false

        return exist;
    }

    // 데이터 값 넘겨줌
    public ReviewLike getData(int no, HttpServletRequest request){
        HttpSession session = request.getSession();
        String user_id = (String)session.getAttribute("log");

        ReviewLike reviewLike = null;
        List<ReviewLike> datas = repo.findAll();
        for(ReviewLike data : datas){
            if(data.getReview_no() == no && data.getUser_id().equals(user_id)){
                reviewLike = data;
            }
        }

        return reviewLike;
    }

    // 테이블에 넣기
    public boolean addData(HttpServletRequest request){
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("log");
        int review_no = Integer.parseInt(request.getParameter("no"));

        ReviewLikeRequestDto newDto = new ReviewLikeRequestDto(user_id, review_no);
        ReviewLike newReviewLike = new ReviewLike(newDto);
        repo.save(newReviewLike);

        return true;
    }

     // 테이블에서 삭제
    public boolean deleteData(HttpServletRequest request){
        int review_no = Integer.parseInt(request.getParameter("no"));
        ReviewLike reviewLike = getData(review_no, request);
        repo.deleteById(reviewLike.getNo());

        return true;
    }
}
