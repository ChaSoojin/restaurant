package com.example.restaurant.service;

import com.example.restaurant.domain.Reserve;
import com.example.restaurant.domain.ReserveRepository;
import com.example.restaurant.domain.ReserveRequestDto;
import com.example.restaurant.domain.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReserveService {
    private final ReserveRepository repo;

    //예약 데이터 저장
    public Reserve reserveData(ReserveRequestDto dto){
        Reserve reserve = new Reserve(dto);
        return repo.save(reserve);
    }

    //내 예약내역 조회
    public List<Reserve> getMyReserve(String user_id){
        List<Reserve> reserveList = repo.findAll();
        List<Reserve> result = new ArrayList<>();

        for(Reserve r : reserveList){
            if(r.getUser_id().equals(user_id)){
                result.add(r);
            }
        }
        return result;
    }

    // ----- 사장페이지 관련 ------

    // 레스토랑 id 로 예약내역 조회
    public List<Reserve> getMyReserveByRestaurantId(String restaurant_id){
        List<Reserve> reserveList = repo.findAll();
        List<Reserve> result = new ArrayList<Reserve>();

        for(Reserve r : reserveList){
            System.out.println(r.getRestaurant_name());
            if(r.getRestaurant_id().equals(restaurant_id)){
                result.add(r);
            }
        }
        return result;
    }
}
