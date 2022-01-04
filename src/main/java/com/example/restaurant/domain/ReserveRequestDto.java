package com.example.restaurant.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ReserveRequestDto {
    private int no, cnt;
    private String user_id, restaurant_id, restaurant_name, phone, reserve_time, user_name;

    public ReserveRequestDto(String user_id, String restaurant_id, String restaurant_name, String phone, String reserve_time, int cnt, String user_name){
        this.user_id = user_id;
        this.restaurant_id = restaurant_id;
        this.restaurant_name = restaurant_name;
        this.phone = phone;
        this.reserve_time = reserve_time;
        this.cnt = cnt;
        this.user_name = user_name;
    }
}
