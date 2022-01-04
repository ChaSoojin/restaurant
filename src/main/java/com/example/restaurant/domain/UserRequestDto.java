package com.example.restaurant.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor // for JPA Repo
@Getter
public class UserRequestDto {
    private int no, separate;
    private String id, pw, name, phone, email;


    // 필요한 생성자 오버로딩
    public UserRequestDto(String id, String pw){
        this.id = id;
        this.pw = pw;
    }

    public UserRequestDto(String id, String pw, String name){
        this.id = id;
        this.pw = pw;
        this.name = name;
    }

    public UserRequestDto(String id, String pw, String name, int separate, String phone, String email){
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.separate = separate;
        this.phone = phone;
        this.email = email;
    }
}