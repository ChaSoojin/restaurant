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
@Table(name = "like_restaurant")

@Entity
public class RestaurantLike extends Timestamped {
    @Id
    private String restaurant_id;

    @NotNull
    @Column
    private String user_id, restaurant_name, phone, address, x, y;

    public RestaurantLike(RestaurantLikeRequestDto dto){
        this.restaurant_id = dto.getRestaurant_id();
        this.user_id = dto.getUser_id();
        this.restaurant_name = dto.getRestaurant_name();
        this.phone = dto.getPhone();
        this.address = dto.getAddress();
        this.x = dto.getX();
        this.y = dto.getY();
    }
}
