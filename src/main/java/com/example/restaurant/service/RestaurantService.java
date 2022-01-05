package com.example.restaurant.service;

import com.example.restaurant.domain.Restaurant;
import com.example.restaurant.domain.RestaurantRepository;
import com.example.restaurant.domain.RestaurantRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantService {
    private final RestaurantRepository repo;

    //맛집 DB 저장
    public Restaurant addRestaurant(RestaurantRequestDto dto){
        Restaurant restaurant = new Restaurant(dto);
        return repo.save(restaurant);
    }

    //맛집 상세페이지 조회
    public List<Restaurant> getRestaurant(String restaurant_id){
        List<Restaurant> restaurantList = repo.findAll();
        List<Restaurant> result = new ArrayList<>();

        for(Restaurant r : restaurantList){
            if(r.getRestaurant_id().equals(restaurant_id)){
                result.add(r);
            }
        }
        return result;
    }

    // ---- 사장 페이지 관련 ----
    // Id로 Restaurant 객체 가져오기
    public Restaurant getRestaurantById(String restaurant_id){
        List<Restaurant> restaurantList = repo.findAll();
        Restaurant result = null;

        for(Restaurant r : restaurantList){
            if(r.getRestaurant_id().equals(restaurant_id)){
                result = r;
            }
        }
        return result;
    }

    @Transactional
    public boolean updateOwner(String restaurant_id, String user_id){
        Restaurant r = getRestaurantById(restaurant_id);
        RestaurantRequestDto dto = new RestaurantRequestDto(r.getRestaurant_id(),user_id,r.getRestaurant_name(),r.getPhone(),r.getAddress(),r.getX(),r.getY());
        r.update(dto);
        return true;
    }

    @Transactional
    public boolean deleteOwner(String restaurant_id){
        Restaurant r = getRestaurantById(restaurant_id);
        RestaurantRequestDto dto = new RestaurantRequestDto(r.getRestaurant_id(),".",r.getRestaurant_name(),r.getPhone(),r.getAddress(),r.getX(),r.getY());
        r.update(dto);
        return true;
    }

    // (user_id에 해당하는) 레스토랑 전부 다 받아서 테이블에 뿌리기
    public List<Restaurant> getRestaurants(String user_id, HttpServletRequest request){
        List<Restaurant> list = repo.findAll();
        List<Restaurant> target = new ArrayList<Restaurant>();

        for(Restaurant r : list){
            if(r.getUser_id().equals(user_id)){
                target.add(r);
                System.out.println(r.getRestaurant_name() + " : " + r.getUser_id());
            }
        }
        request.setAttribute("list", target);
        return target;
    }
}
