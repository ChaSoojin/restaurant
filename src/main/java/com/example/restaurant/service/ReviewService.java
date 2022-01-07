package com.example.restaurant.service;

import com.example.restaurant.domain.Review;
import com.example.restaurant.domain.ReviewRepository;
import com.example.restaurant.domain.ReviewRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository repo;

    // 1. create
    public Review addReview(ReviewRequestDto reviewRequestDto){
        Review review = new Review(reviewRequestDto);
        return repo.save(review);
    }

    // 2. Read
    @Transactional
    public Review getReview(int no){
        Review review = null;
        review = repo.findById(no).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 리뷰입니다.")
        );
        review.updateView(review.getView() + 1);
        System.out.println(review.getView() + "개");
        return review;
    }

    public List<Review> getReviews(){
        List<Review> list = null;
        list = repo.findAll();
        return list;
    }

    // 3. Update
    @Transactional
    public boolean updateReview(int no, ReviewRequestDto reviewRequestDto){
        Review review = getReview(no);
        review.update(reviewRequestDto);
        return true;
    }

    // 4. Delete
    @Transactional
    public int deleteReview(int no){
        Review review = getReview(no);
        repo.deleteById(review.getNo());
        return review.getNo();
    }

    public List<Review> getReviewByRestaurantId(HttpServletRequest request){
        String restaurant_id = request.getParameter("restaurant_id");
        List<Review> reviewList = repo.findAll();
        List<Review> review = new ArrayList<>();

        for(Review r : reviewList){
            if(r.getRestaurant_id().equals(restaurant_id)){
                System.out.println("ID: " + restaurant_id + " NAME: " + r.getTitle());
                review.add(r);
            }
        }
        return review;
    }

    // 좋아요 + 1
    @Transactional
    public boolean addLike(int no){
        Review review = getReview(no);
        int likes = review.getLikes() + 1;
        review.updateLike(likes);
        return true;
    }

    // 좋아요 - 1
    @Transactional
    public boolean minusLike(int no){
        Review review = getReview(no);
        int likes = review.getLikes() - 1;
        review.updateLike(likes);
        return true;
    }

    // ----- 사장페이지 관련 ------

    // 레스토랑 id 로 리뷰list 조회
    public boolean getMyReviewByRestaurantId(HttpServletRequest request){
        String restaurant_id = request.getParameter("restaurant_id");
        List<Review> reviewList = repo.findAll();
        List<Review> review = new ArrayList<>();

        for(Review r : reviewList){
            if(r.getRestaurant_id().equals(restaurant_id)){
                review.add(r);
            }
        }
        request.setAttribute("review", review);
        return true;
    }

    // 리뷰 리스트 -> 상세 리뷰
    public Review getReviewWithoutView(HttpServletRequest request){
        int no = Integer.parseInt(request.getParameter("no"));
        Review review = null;
        review = repo.findById(no).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 리뷰입니다.")
        );
        request.setAttribute("review", review);
        return review;
    }

    // update comment
    @Transactional
    public boolean updateComment(HttpServletRequest request){
        int no = Integer.parseInt(request.getParameter("no"));
        String comment = request.getParameter("comment");
        Review review = getReviewWithoutView(request);
        review.updateComment(comment);
        return true;
    }


}