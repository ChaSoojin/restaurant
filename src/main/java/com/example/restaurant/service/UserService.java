package com.example.restaurant.service;


import com.example.restaurant.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepo;
    private final ReserveRepository reserveRepo;
    private final ShopRepository shopRepo;
    private final ReviewRepository reviewRepo;


    private final ReviewService reviewService;

    //Service 클래스는 비지니스로직을 담음

    //CRUD
    //1. Create
    public Boolean addUser(UserRequestDto userRequestDto) {
        User user = new User(userRequestDto);
        List<User> users = getUsers();
        boolean chk = true;

        for (User u : users) {
            if (u.getId().equals(user.getId())) {
                chk = false;
            }
        }
        return chk;
    }


    public User findUser(String id) {
        List<User> users = getUsers();

        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public Review findReview(String id) {
        List<Review> reviews = getReviews();

        for (Review review : reviews) {
            if (review.getUser_id().equals(id)) {
                return review;
            }
        }
        return null;
    }


    //유저 들!!
    public List<User> getUsers() {
        List<User> users = null;
        users = userRepo.findAll();
        return users;
    }

    //아이디값으로 찾은 리뷰들!!
    public List<Review> getReviews() {
        List<Review> reviews = null;
        reviews = reviewRepo.findAll();
        return reviews;
    }

    public boolean checkLogin(String id, String pw, HttpServletRequest request) {
        List<User> users = getUsers();
        String logCheck = null;

        // 로그인 되는지 체크
        for (User user : users) {
            if (user.getId().equals(id) && user.getPw().equals(pw)) {
                System.out.println("ID: " + id + " PW: " + pw + " 유저아이디: " + user.getId() + " 유저비번: " + user.getPw());

                int seperate = getSeparate(id);
                request.setAttribute("message", "로그인하셨습니다.");

                HttpSession session = request.getSession();
                session.setAttribute("seperate", seperate);
                logCheck = id;
            } else {
                request.setAttribute("message", "로그인에 실패했습니다.");
            }
        }
        if (logCheck != null) {
            HttpSession session = request.getSession();  //세션 객체 생성
            session.setAttribute("log", id);  // vo에 저장한 유저정보를 세션  값으로 입력
//            request.setAttribute("log", logCheck);
            //로그값확인하기위해 출력
            System.out.println("로그값!!=====" + logCheck);
            return true;
        }
        return false;
    }


    // 3. Update
    @Transactional                                                  //기존의 테이블에 쿼리의 변화 일어나야함
    public boolean updateUser(String id, UserRequestDto dto) {
        User user = findUser(id);
        user.update(dto);
        return true;
    }

    //4. Delete
    @Transactional
    public void deletUser(String id, String pw, HttpServletRequest request) {
        List<User> users = getUsers();
        User user = findUser(id);
        System.out.println();
        Review review = findReview(id);

        if (user.getPw().equals(pw)) {

            userRepo.deleteById(user.getNo());

            reserveRepo.deleteByAllId(id);

            reviewRepo.deleteByAllId(id);
            shopRepo.deleteByAllId(id);

            HttpSession session = request.getSession();
            session.removeAttribute("log");
        }
    }

    public User getUser(String id) {
        User user = findUser(id);
        user = userRepo.findById(user.getNo()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 리뷰입니다.")
        );

        return user;
    }

    public int getSeparate(String id) {
        User user = findUser(id);
        return user.getSeparate();
    }
}