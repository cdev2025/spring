package com.example.mywebservice.dto;

// 회원가입 DTO

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class AddUserDto {
    private String email;
    private String password;
    private String name;
}
