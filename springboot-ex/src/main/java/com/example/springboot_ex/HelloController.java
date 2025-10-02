package com.example.springboot_ex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HelloController {

    @Autowired
    private AppProperties appProperties;

    // 기본 인사 API
    // http://localhost:8080/api/hello
    @GetMapping("/hello")
    public String hello(){
        return appProperties.getMessage();
    }

    // 이름을 받아서 인사하는 API
    // http://localhost:8080/api/greet/elice
    @GetMapping("/greet/{name}")
    public Map<String, String> greet(@PathVariable String name){
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello, " + name+"!");
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("profile_message", appProperties.getMessage());
        return response;
    }

    // 사용자 생성 API
    // http://localhost:8080/api/users : body에 JSON 데이터 넣어서 요청
    @PostMapping("/users")
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody Map<String, String> userData){
        // 간단한 유효성 검사
        if(!userData.containsKey("name") || userData.get("name").trim().isEmpty()){
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Name is required");
            return ResponseEntity.badRequest().body(errorResponse);
        }

        // 사용자 생성 (실제로는 DB에 저장)
        Map<String, Object> createUser = new HashMap<>();
        createUser.put("id", System.currentTimeMillis());
        createUser.put("name", userData.get("name"));
        createUser.put("email", userData.getOrDefault("email", ""));
        createUser.put("createAt", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.CREATED).body(createUser);
    }

    // 사용자 조회 API
    // http://localhost:8080/api/users/1
    @GetMapping("users/{id}")
    public ResponseEntity<Map<String, Object>> getUser(@PathVariable Long id) {
        if (id <= 0){
            return ResponseEntity.notFound().build();
        }

        // 사용자를 실제로는 DB에서 조회
        Map<String, Object> user = new HashMap<>();
        user.put("id", id);
        user.put("name", "John Doe");
        user.put("email", "john@test.com");
        user.put("createdAt", LocalDateTime.now());

        return ResponseEntity.ok(user);
    }

}
