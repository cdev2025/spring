package com.example.mysqltest.service;

import com.example.mysqltest.entity.User;
import com.example.mysqltest.repository.UserRepository;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User save(String name){
        return userRepository.save(User.builder().name(name).build());
    }

    public long countUsers(){
        return userRepository.count();
    }
}
