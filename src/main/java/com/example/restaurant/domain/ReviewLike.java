package com.example.restaurant.domain;

import com.example.restaurant.util.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "like_review")
public class ReviewLike extends Timestamped {
    @Id
    private int no;

    @Column
    @NonNull
    private String user_id;

    @Column
    @NonNull
    private int review_no;

    public ReviewLike(ReviewLikeRequestDto reviewLikeRequestDto){
        this.user_id = reviewLikeRequestDto.getUser_id();
        this.review_no = reviewLikeRequestDto.getReview_no();
    }
}
