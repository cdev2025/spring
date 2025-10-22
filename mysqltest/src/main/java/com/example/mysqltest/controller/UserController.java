package com.example.mysqltest.controller;

import com.example.mysqltest.entity.User;
import com.example.mysqltest.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Users", description="사용자 관리 API")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "DB 연결 상태 확인", description = "users  테이블의 현재 행 개수로 연결 상태를 확인합니다.")
    // http://localhost:8080/api/users/db-check
    @GetMapping("/db-check")
    public String checkConeection(){
        return "✅ DB 연결 OK, users 행 개수 = " + userService.countUsers();
    }

    @Operation(summary = "전체 사용자 조회")
    @GetMapping
    public List<User> getAllUsers(){
        return userService.findAll();
    }

    @Operation(summary = "새 사용자 등록")
    @PostMapping
    public User createUser(@RequestParam String name){
        return userService.save(name);
    }

}
