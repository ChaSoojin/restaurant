package com.example.restaurant.service;

import com.example.restaurant.domain.Review;
import com.example.restaurant.domain.ReviewRepository;
import com.example.restaurant.domain.ReviewRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
}