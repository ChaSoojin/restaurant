package com.example.restaurant.controller;

import com.example.restaurant.domain.User;
import com.example.restaurant.domain.UserRequestDto;
import com.example.restaurant.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@RestController
@Controller
public class UserController {

    private final UserService service;

//api 구현


// CRUD
// 1. Create

//    @RequestMapping("/v1/users")
// public User addUser(@RequestBody UserRequestDto userRequestDto){

    //회원가입
    @PostMapping("/v1/users")
    public void addUser(@RequestBody UserRequestDto userRequestDto, HttpServletResponse response) throws IOException {
        System.out.println("=======______++++++++=======");
        // return service.addUser(userRequestDto);

        User user = service.addUser(userRequestDto);

        if(user != null){
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('회원가입 되었습니다.');</script>");
            out.flush();
            System.out.println("성공");
        }
        else{
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('중복 아이디가 존재합니다.');</script>");
            out.flush();
            System.out.println("실패");
        }

        System.out.println(" UserConteroller 의 addUser메소드의 service.addUser(userRequestDto) 후");

    }
//

    // 2. Read
    @GetMapping("/v1/users/{id}")
    public User getUser(@PathVariable String id, String pw){
        return service.getUser(id, pw );

    }

    //getUsers 메소드 완성 -> 디엠으로 제출 (브라우저 또는 API플랫폼에서 get 요청 결과물 캡쳐)
    @GetMapping("/v1/users")
    public List<User> getUsers(){
        return service.getUsers();
// List<User> users = null;
// users = repo.findAll();
// return users;
    }


    public String  checkLogin(@RequestParam Map<String, String> data,HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = (String) data.get("id");
        String pw = (String) data.get("pw");

        if(service.checkLogin(id, pw, request)){
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('로그인 되었습니다.');</script>");
            out.flush();

            return "main";
        }
        else{
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('아이디, 패스워드를 확인해주세요!');</script>");
            out.flush();

            return "user/login";
        }

    }

    // 3. Update
    @PutMapping("/v1/users/{code}")
    public User updateUser(@PathVariable int code, @RequestBody UserRequestDto userRequestDto ){
        return service.updateUser(code, userRequestDto);
    }

    // 4. Delete
    @DeleteMapping("/v1/users/{code}")
    public int deleteUser(@PathVariable int code){
        return service.deletUser(code);
    }
}