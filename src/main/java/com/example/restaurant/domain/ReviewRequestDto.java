package com.example.restaurant.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ReviewRequestDto {
    private int no;
    private String user_id,restaurant_id, title, content;
    private int likes;

    public ReviewRequestDto(String user_id, String restaurant_id, String title, String content){
        this.user_id = user_id;
        this.restaurant_id = restaurant_id;
        this.title = title;
        this.content = content;
    }
    //    public ReviewRequestDto(String user_id, String restaurant_id, String title, String content, int likes){
//        this.user_id = user_id;
//        this.restaurant_id = restaurant_id;
//        this.title = title;
//        this.content = content;
//        this.likes = likes;
//    }
    public ReviewRequestDto(int no, String title, String content){
        this.no = no;
        this.title = title;
        this.content = content;
    }
}