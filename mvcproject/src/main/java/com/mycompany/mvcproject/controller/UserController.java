package com.mycompany.mvcproject.controller;

import com.mycompany.mvcproject.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user") // http://localhost:8080/user
public class UserController {

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
}
