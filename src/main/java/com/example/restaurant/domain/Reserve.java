package com.example.restaurant.domain;

import com.example.restaurant.util.Timestamped;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@Table(name = "reserve")

@Entity
public class Reserve extends Timestamped {
    @Id
    private int no;

    @NotNull
    @Column
    private String user_id, restaurant_id, restaurant_name, phone, reserve_time, user_name;
    private int cnt;

    public Reserve(ReserveRequestDto dto){
        this.user_id = dto.getUser_id();
        this.restaurant_id = dto.getRestaurant_id();
        this.restaurant_name = dto.getRestaurant_name();
        this.phone = dto.getPhone();
        this.reserve_time = dto.getReserve_time();
        this.cnt = dto.getCnt();
        this.user_name = dto.getUser_name();
    }
}
