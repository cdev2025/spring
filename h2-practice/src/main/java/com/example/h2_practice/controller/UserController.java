package com.example.h2_practice.controller;

import com.example.h2_practice.dto.UserDto;
import com.example.h2_practice.entity.User;
import com.example.h2_practice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 사용자 생성 api
    @PostMapping
    public User createUser(@RequestBody UserDto userDto){
        return userService.createUser(userDto);
    }

    // 사용자 조회
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    // 사용자 정보 업데이트
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UserDto userDto){
        return userService.updateUser(id, userDto);
    }
}
