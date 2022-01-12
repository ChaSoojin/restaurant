package com.example.restaurant.controller;



import com.example.restaurant.domain.User;
import com.example.restaurant.domain.UserRepository;
import com.example.restaurant.domain.UserRequestDto;
import com.example.restaurant.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@RestController // @Controller + @ResponseBody (JSON)
@Controller
public class UserController {
    private final UserRepository repo;
    private final UserService service;

//api 구현


// CRUD
// 1. Create

//    @RequestMapping("/v1/users")
// public User addUser(@RequestBody UserRequestDto userRequestDto){

    //회원가입
    @PostMapping("/v1/users")
    public boolean addUser(@RequestBody UserRequestDto userRequestDto, HttpServletRequest request) {
        System.out.println("=======______++++++++=======");
        User user = new User(userRequestDto);

        if(service.addUser(userRequestDto)){
            System.out.println("성공.." + user.getId());
            repo.save(user);
            request.setAttribute("proc", "success");

            return true;
        }
            System.out.println("실패.."+ user.getId());
            request.setAttribute("proc", "fail");

        return false;
    }


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

            return "/user/login";
        }
    }


    //클라이언트로 유저의데이터를 가져오기위한 메소드
    public void getUser( HttpServletRequest request){
        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("log");
        System.out.println("id===="+id);

        searchUser(id,request);
    }

    public void searchUser(String id, HttpServletRequest request){
        User user = service.getUser(id);
        request.setAttribute("loginUser", user);
    }


    //    유저업데이트
    public String updateUser(@RequestParam Map<String, String> updateFormData, HttpServletRequest request){

        String id = updateFormData.get("id");
        System.out.println("id======"+id);
        String pw = (String) updateFormData.get("pw");
        String phone = (String) updateFormData.get("phone");
        String email = (String) updateFormData.get("email");
        UserRequestDto dto = new UserRequestDto(pw,phone,email);
        System.out.println("유저컨트롤러 업데이트유저메소드 안 ===");
        if(service.updateUser(id, dto)) {
            System.out.println("업데이트 성공!");
        }
        //어디로 이동시킬지 조원과 상의할것 마이페이지로 다시 이동시키는게 나을거같은게 내생각
        return "main";

    }

    public String deleteUser(Map<String, String> data, HttpServletRequest request){
        String id = data.get("id");
        String pw = data.get("pw");
        service.deletUser(id, pw, request);
        return "main";
    }



}