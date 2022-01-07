package com.example.restaurant.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ReviewLikeRequestDto {
    private int no;
    private String user_id;
    private int review_no;

    public ReviewLikeRequestDto(String user_id, int review_no){
        this.user_id = user_id;
        this.review_no = review_no;
    }
}
