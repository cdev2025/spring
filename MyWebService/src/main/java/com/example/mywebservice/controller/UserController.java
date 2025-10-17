package com.example.mywebservice.controller;

import com.example.mywebservice.dto.AddUserDto;
import com.example.mywebservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("signup")
    public String signup(){
        return "signup";
    }

    @PostMapping("/user")
    public String signup(AddUserDto dto, RedirectAttributes redirectAttributes){
        try{
            userService.save(dto);
            redirectAttributes.addFlashAttribute("message", "회원가입이 완료되었습니다. 로그인해주세요.");
            return "redirect:/login";
        }catch (Exception e){
            log.error("회원가입 실패", e);
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/signup";
        }
    }
}
