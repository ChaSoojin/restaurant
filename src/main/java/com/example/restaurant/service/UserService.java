package com.example.restaurant.service;

import com.example.restaurant.domain.User;
import com.example.restaurant.domain.UserRepository;
import com.example.restaurant.domain.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository repo;

    //Service 클래스는 비지니스로직을 담음

    //CRUD
    //1. Create
    public User addUser(UserRequestDto userRequestDto){
        System.out.println("UserService.java 얘가문제냐");
        User user = new User(userRequestDto);

        return repo.save(user);
    }


    //2. Read
    public User getUser(String id, String pw  ){
        List<User> users = getUsers();

        for(User user : users){
            if(user.getId().equals(id) && user.getPw().equals(pw)){

//                HttpSession session = request.getSession();
//                session.setAttribute("log",id);
                return user;
            }
        }
        return null;

    }


    public List<User> getUsers(){
        List<User> users = null;
        users = repo.findAll();
        return  users;
    }
    public boolean checkLogin(String id, String pw, HttpServletRequest request){
        List<User> users = getUsers();
        System.out.println("사이즈: " + users.size());

        String logCheck = null;
        String msg = "";

        // 로그인 되는지 체크
        for(User user : users) {
            System.out.println("ID: " + id + " PW: " + pw + " 유저아이디: " + user.getId() + " 유저비번: " + user.getPw());

            if (user.getId().equals(id) && user.getPw().equals(pw)) {
                System.out.println("1) ID: " + id + " PW: " + pw + " 유저아이디: " + user.getId() + " 유저비번: " + user.getPw());
                msg = "로그인하셨습니다.";
                logCheck = id;
                break;
            } else {
                System.out.println("2) ID: " + id + " PW: " + pw + " 유저아이디: " + user.getId() + " 유저비번: " + user.getPw());
                msg = "로그인에 실패했습니다.";
            }
        }

        request.setAttribute("message", msg);

        if(logCheck != null){
            HttpSession session = request.getSession();  //세션 객체 생성
            session.setAttribute("log", id);  // vo에 저장한 유저정보를 세션  값으로 입력
//            request.setAttribute("log", logCheck);
            //로그값확인하기위해 출력
            System.out.println("로그값!!====="+logCheck);
            return true;
        }
        return false;
    }

    // 3. Update
    @Transactional  //기존의 테이블에 쿼리가 일어나야함을 알려줌
    public User updateUser(int no,UserRequestDto userRequestDto){
//        User user = repo.findById(code).orElse(
//                () -> new IllegalArgumentException("존재하지 않는 사용자입니다.")
//        );
//        User user = getUser(no);
//        user.update(userRequestDto);
//        return user;
        return null;
    }

    //4. Delete
    @Transactional
    public int deletUser(int no){
//        User  user = getUser(no);
//        repo.deleteById(user.getNo());
//        return user.getNo();
        return 0;

    }

}