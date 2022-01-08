package com.example.restaurant.service;

import com.example.restaurant.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantLikeService {
    private final RestaurantLikeRepository repo;

    public List<RestaurantLike> getLike(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("log");

        List<RestaurantLike> likes = repo.findAll();
        List<RestaurantLike> result = new ArrayList<>();

        for (RestaurantLike like : likes) {
            if (like.getUser_id().equals(user_id)) {
                result.add(like);
            }
        }
        System.out.println("사이즈: " + result.size());
        return result;
    }

    public boolean addLike(RestaurantLikeRequestDto dto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("log");

        RestaurantLikeRequestDto tmpdto = new RestaurantLikeRequestDto(dto.getRestaurant_id(), user_id, dto.getRestaurant_name(), dto.getPhone(), dto.getAddress(), dto.getX(), dto.getY());
        RestaurantLike newdto = new RestaurantLike(tmpdto);

        repo.save(newdto);
        return true;
    }

    @Transactional
    public void cancelLike(String restaurant_id, HttpServletRequest request) {

        List<RestaurantLike> likes = getLike(request);

        System.out.println("Like 사이즈: " + likes.size());

        for (RestaurantLike like : likes) {
            if (like.getRestaurant_id().equals(restaurant_id)) {
                System.out.println("true");

                repo.deleteById(restaurant_id);
            }
        }
    }
}