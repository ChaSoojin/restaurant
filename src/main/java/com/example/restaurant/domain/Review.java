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
@Table(name = "review")
public class Review extends Timestamped {
    @Id
    private int no;

    @Column
    @NonNull
    private String user_id, restaurant_id, title, content;

    @Column
    private int likes, view;

    @Column
    private String comment;

    public Review(ReviewRequestDto reviewRequestDto){
        this.user_id = reviewRequestDto.getUser_id();
        this.restaurant_id = reviewRequestDto.getRestaurant_id();
        this.title = reviewRequestDto.getTitle();
        this.content = reviewRequestDto.getContent();
        this.likes = reviewRequestDto.getLikes();
        this.comment = reviewRequestDto.getComment();
    }

    public void update(ReviewRequestDto reviewRequestDto){
        this.title = reviewRequestDto.getTitle();
        this.content = reviewRequestDto.getContent();
    }

    public void updateView(int viewNo){
        this.view = viewNo;
    }

    public void updateComment(String comment){
        this.comment = comment;
    }
}