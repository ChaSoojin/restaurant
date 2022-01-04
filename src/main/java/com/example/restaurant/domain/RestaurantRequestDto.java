package com.example.restaurant.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RestaurantRequestDto {
    private int no;
    private String restaurant_id, user_id, restaurant_name, phone, address, x, y;
    private int likes;

    public RestaurantRequestDto(String restaurant_id, String user_id, String restaurant_name, String phone, String address, String x, String y){
        this.restaurant_id = restaurant_id;
        this.user_id = user_id;
        this.restaurant_name = restaurant_name;
        this.phone = phone;
        this.address = address;
        this.x = x;
        this.y = y;
    }
}
