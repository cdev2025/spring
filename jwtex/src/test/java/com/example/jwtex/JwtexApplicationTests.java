package com.example.jwtex;

import com.example.jwtex.service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashMap;

@SpringBootTest
class JwtexApplicationTests {

    @Autowired
    private JwtService jwtService;

	@Test
	void contextLoads() {
	}

    @Test
    void tokenCreate(){
        var claims = new HashMap<String, Object>();
        claims.put("user_id", 923);

        //var expiredAt = LocalDateTime.now().plusMinutes(10);
        var expiredAt = LocalDateTime.now().plusSeconds(10);

        var jwtToken = jwtService.create(claims, expiredAt);

        System.out.println(jwtToken);
    }

    @Test
    void tokenValidation(){
        var token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjo5MjMsImV4cCI6MTc2MzY5MDkxOX0.VIDJxsZu2M5Tz6Nd1OaCEWg5SBtIPCv6yfhJ-Zoyc18";

        jwtService.validation(token);
    }

}
