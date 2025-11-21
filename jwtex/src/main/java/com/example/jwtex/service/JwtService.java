package com.example.jwtex.service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

@Slf4j
@Service
public class JwtService {
    /** 바로 알 수 있도록 Base64 인코딩 사용하지 않은 수수 문자열 기반 secretKey
     * - HS256 알고리즘은 최소 32바이트 이상의 키 길이를 요구
     * - 문자여을 UTF-8로 byte 변환하면 32바이트 이상이 되도록
     * - jwt.io에서 SECCRET 입력란에 그대로 입력하면 Valid Signature 검증됨.
     * */
    private static final String secretKey = "SpringSecurityKey_P_ssword_http__Spring_io";

    /**
     * 실제 JWT 서명에 사용되는 Key 객체 생성
     * - Keys.hmacShaKeyFor()는 전달받은 byte[]를 그대로 HMAC_SHA 키로 사용
     * -    secretKey -> UTF-8 bytes로 변환한 값 그대로 사용
     * */
    private final javax.crypto.SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

    /**
     * JWT 토큰 생성 메서드
     *
     * @param claims    Payload에 넣고 싶은 데이터(Map)
     * @param expireAt  만료 시간(LocalDateTime)
     * @return          완성된 JWT 문자열(token)
     *
     *
     * [동작]
     * - JJWT 라이브러리의 builder()를 통해 토큰 생성
     * - signWith(key, HS256) : key로 HMAC_SHA256 방식 서명
     * - .claims(claims)       : payload에 데이터 추가
     * - .expiration(date)      : 만료 시간 설정(exp)
     * */
    public String create(Map<String, Object> claims, LocalDateTime expireAt){
        // LocalDateTime -> Date 변환 (JJWT는 java.util.Date 사용)
        var _expireAt = Date.from(expireAt.atZone(ZoneId.systemDefault()).toInstant());

        return Jwts.builder()
                .signWith(key, Jwts.SIG.HS256) // HS256으로 서명
                .claims(claims)                // Palyload에 claims 데이터 추가
                .expiration(_expireAt)          // exp(만료 시간) 설정
                .compact();                     // 최종 JWT String으로 변환
    }

    /**
     * JWT 토큰 검증 메서드
     *
     * @param token  검증할 JWT 문자열
     *
     * [동작]
     * - parser.verifyWith(key) : 생성 시 사용한 동일한 key로 서명 검증
     * - parseSignedClaims() : Signature + Payload + Header 모두 검증
     *               - 서명이 올바르지 않으면 SignatureException 발생
     *               - 만료 시간이 지나면 ExpriredException 발생
     * */
    public void validation(String token){

        var parser = Jwts.parser()
                .verifyWith(key)    // 토큰 만들 때 썼던 동일한 key로 서명 검증
                .build();


        try {
            var result = parser.parseSignedClaims(token);

            // payload 값들 확인
            result.getPayload().forEach((k, v) -> log.info("key: {}, value: {}", k, v));
        }catch(Exception e){

            // Signature 불일치 -> 위조된 토큰
            if(e instanceof SignatureException){
                throw new RuntimeException("JWT Token Not Valid Exception");
            } else if(e instanceof ExpiredJwtException){
                throw new RuntimeException("JWT Token Expired Exception");
            } else {
                throw new RuntimeException("JWT Exception");
            }
        }

        //

    }
}
