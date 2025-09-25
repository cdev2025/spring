package com.mycompany.mvcproject.controller;

import com.mycompany.mvcproject.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    // http://localhost:8080/login
    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("user", new User());
        return "login"; // /WEB-INF/views/login.jsp
    }

    @RequestMapping(value="/login", method= RequestMethod.POST)
    public String loginProcess(@ModelAttribute User user, Model model){
        if( User.DEFAULT_EMAIL.equals(user.getEmail()) && User.DEFAULT_PASSWORD.equals(user.getPassword())){
            // 로그인 성공
            model.addAttribute("name", User.DEFAULT_NAME);
            return "loginSuccess"; // /WEB-INF/views/loginSuccess.jsp
        } else {
            // 로그인 실패
            return "redirect:/login";
        }
    }
}
