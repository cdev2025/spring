package com.mycompany.mvcproject.controller;

import com.mycompany.mvcproject.domain.LoginForm;
import com.mycompany.mvcproject.domain.User;
import com.mycompany.mvcproject.service.LoggedUserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user") // http://localhost:8080/user
public class UserController {
    private final LoginForm loginForm;
    private final LoggedUserManagementService loggedUserManagementService;

    @Autowired
    public UserController(LoginForm loginForm, LoggedUserManagementService loggedUserManagementService) {
        this.loginForm = loginForm;
        this.loggedUserManagementService = loggedUserManagementService;
    }

    // http://localhost:8080/user/signup
    @RequestMapping("/signup") //GET
    public String signup(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "signup"; // /WEB-INF/views/signup.jsp
    }

    @RequestMapping(value="/signup", method= RequestMethod.POST)
    // @ResponseBody
    // public String signup(@ModelAttribute("user") User user)
    public String signup(@ModelAttribute User user){
        System.out.println("[ user ] " + user);
        // return "success"; // @ResponseBody인 경우 => String "success" 그대로 return
        return "redirect:http://localhost:8080/hello";
    }

    // Request Scope 실습
    // http://localhost:8080/user/login
    @GetMapping("/login")
    public String loginForm(Model model){
        System.out.println("로그인 폼 요청 - 인스턴스 ID: " + loginForm.getInstanceId() );
        model.addAttribute("loginForm", loginForm);
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        Model model){
        System.out.println("로그인 처리 요청 - 인스턴스 ID: " + loginForm.getInstanceId() );

        // RequestScope 빈에 데이터 설정
        loginForm.setEmail(email);
        loginForm.setPassword(password);

        if(loginForm.authenticate()){
            // 세션에 사용자 정보 저장
            loggedUserManagementService.login("테스트사용자", email);
            return "redirect:/main"; //메인 페이지로 리디렉션
        }else {
            model.addAttribute("message", "로그인 실패 - 이메일이나 비밀번호를 확인하세요.");
            model.addAttribute("loginForm", loginForm);
            return "login";
        }
    }
}
