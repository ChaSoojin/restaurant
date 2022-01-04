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
@Table(name = "restaurant")

@Entity
public class Restaurant extends Timestamped {
    @Id
    private int no;

    @NotNull
    @Column
    private String restaurant_id, user_id, restaurant_name, phone, address, x, y;

    @Column
    private int likes;

    public Restaurant(RestaurantRequestDto dto){
        this.restaurant_id = dto.getRestaurant_id();
        this.user_id = dto.getUser_id();
        this.restaurant_name = dto.getRestaurant_name();
        this.phone = dto.getPhone();
        this.address = dto.getAddress();
        this.x = dto.getX();
        this.y = dto.getY();
    }
}
