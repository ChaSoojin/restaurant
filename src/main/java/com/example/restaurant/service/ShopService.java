package com.example.restaurant.service;

import com.example.restaurant.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ShopService {

    private final ShopRepository repo;
    //1. Create
    public Boolean addShop(ShopRequestDto shopRequestDto, HttpServletRequest request){

        HttpSession session = request.getSession();

        Shop shop = new Shop(shopRequestDto);
        String user_id = (String) session.getAttribute("log");
        System.out.println(user_id);

        shop.updateUserId(user_id);

        List<Shop> shops = getShops();
        boolean chk = true;

        for(Shop s : shops){
            if(s.getRestaurant_id().equals(shop.getRestaurant_id())){
                chk = false;
            }
        }

        if(chk == true){
            repo.save(shop);
            System.out.println("성공.." + shop.getRestaurant_name());
        }else{
            System.out.println("실패..");
        }

        return chk;
    }

    // 2. read
    public List<Shop> getShops(){
        List<Shop> shops = null;
        shops = repo.findAll();
        return  shops;
    }

    // (user_id에 해당하는) 레스토랑 전부 다 받아서 테이블에 뿌리기
    public List<Shop> getShops(HttpServletRequest request){
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("log");

        List<Shop> list = repo.findAll();
        List<Shop> target = new ArrayList<Shop>();

        for(Shop s : list){
            if(s.getUser_id().equals(user_id)){
                target.add(s);
                System.out.println(s.getRestaurant_name() + " : " + s.getUser_id());
            }
        }
        System.out.println("-----------------");
        request.setAttribute("list", target);
        return target;
    }

    // Id로 Restaurant 객체 가져오기
    public Shop getShopById(String restaurant_id){
        List<Shop> shopList = repo.findAll();
        Shop result = null;

        for(Shop r : shopList){
            if(r.getRestaurant_id().equals(restaurant_id)){
                result = r;
            }
        }
        return result;
    }

    // 레스토랑 삭제
    @Transactional
    public boolean deleteShop(HttpServletRequest request){
        String restaurant_id = request.getParameter("restaurant_id");
        repo.deleteByStringId(restaurant_id);
        return true;
    }

    // 아예 지우고 싶은데 deletebyId는 int 값만 가능하다고
//    //4. Delete
//    @Transactional
//    public int deleteShop(String restaurant_id){
//        Shop shop = getShopById(restaurant_id);
//        int id = Integer.parseInt(shop.getRestaurant_id());
//        repo.deleteById(id);
//        return id;
//    }
}
