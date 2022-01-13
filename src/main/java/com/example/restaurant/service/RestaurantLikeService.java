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
    private final RestaurantRepository repo2;

    //내가 찜한 레스토랑 목록 조회 
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

    public boolean checkLike(RestaurantLikeRequestDto dto, HttpServletRequest request){
        List<RestaurantLike> list = repo.findAll();
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("log");

        for(RestaurantLike like : list){
            if(like.getRestaurant_id().equals(dto.getRestaurant_id()) && like.getUser_id().equals(user_id)){
                return true;
            }
        }
        return false;
    }

    //식당 찜하기
    public boolean addLike(RestaurantLikeRequestDto dto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("log");

        RestaurantLikeRequestDto tmpdto = new RestaurantLikeRequestDto(dto.getRestaurant_id(), user_id, dto.getRestaurant_name(), dto.getPhone(), dto.getAddress(), dto.getX(), dto.getY());
        RestaurantLike newdto = new RestaurantLike(tmpdto);

        List<Restaurant> list = repo2.findAll();
        Restaurant rt = new Restaurant();

        for(Restaurant r : list){
            if(r.getRestaurant_id().equals(newdto.getRestaurant_id())){
                rt = r;
                break;
            }
        }
        System.out.println("[전]R : " + rt.getRestaurant_name() + " " + rt.getLikes());
        rt.addLikes(rt.getLikes()+1);

        System.out.println("[후]R : " + rt.getRestaurant_name() + " " + rt.getLikes());
        repo.save(newdto);
        return true;
    }

    //식당 찜하기 취소
    @Transactional
    public void cancelLike(String restaurant_id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("log");

        List<RestaurantLike> likes = getLike(request);

        System.out.println("Like 사이즈: " + likes.size());

        for (RestaurantLike like : likes) {
            if (like.getRestaurant_id().equals(restaurant_id) && like.getUser_id().equals(user_id)) {
                System.out.println("true");

                repo.deleteById(restaurant_id);
            }
        }
    }
}