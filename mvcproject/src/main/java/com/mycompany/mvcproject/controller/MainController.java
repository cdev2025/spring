package com.mycompany.mvcproject.controller;

import com.mycompany.mvcproject.service.LoggedUserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    private final LoggedUserManagementService loggedUserManagementService;

    @Autowired
    public MainController(LoggedUserManagementService loggedUserManagementService) {
        this.loggedUserManagementService = loggedUserManagementService;
    }

    // http://localhost:8080/main
    @GetMapping("/main")
    public String main(@RequestParam(required = false) String logout, Model model){
        System.out.println("메인 페이지 접근 - 세션 ID : "  + loggedUserManagementService.getSessionId());

        // 로그아웃 처리
        if( logout!= null){
            loggedUserManagementService.logout();
            model.addAttribute("message", "성공적으로 로그아웃 되었습니다.");
            return "redirect:/user/login";
        }

        // 로그인 여부 확인
        if (!loggedUserManagementService.isLoggedIn()){
            System.out.println("비로그인 사용자의 메인 페이지 접근 시도");
            return "redirect:/user/login";
        }

        // 로그인된 사용자 종보를 뷸에 전달
        model.addAttribute("userName", loggedUserManagementService.getUserName());
        model.addAttribute("userEmail", loggedUserManagementService.getUserEmail());
        model.addAttribute("loginTime", loggedUserManagementService.getLonginTime());
        model.addAttribute("sessionId", loggedUserManagementService.getSessionId());

        return "main";
    }

}
