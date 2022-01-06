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
@Table(name="shop")
public class Shop extends Timestamped {
    @Id
    private String restaurant_id;

    @Column
    @NonNull
    private String user_id, restaurant_name, phone, address, x, y;

    public Shop(ShopRequestDto dto){
        this.restaurant_id = dto.getRestaurant_id();
        this.user_id = dto.getUser_id();
        this.restaurant_name = dto.getRestaurant_name();
        this.phone = dto.getPhone();
        this.address = dto.getAddress();
        this.x = dto.getX();
        this.y = dto.getY();
    }

    //사장님 아이디 추가
    public void update(ShopRequestDto dto){
        this.user_id = dto.getUser_id();
    }

    public void updateUserId(String user_id){
        this.user_id = user_id;
    }

}