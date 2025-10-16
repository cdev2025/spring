package com.example.board.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * 게시글 엔티티
 * setter 사용 지양
 * 명시적 update 메서드로 상태 변경 관리
 * **/
@Entity
@Table(name = "posts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title; // 제목

    @Column(columnDefinition = "TEXT") // columnDefinition : DB에 생성될 컬럼의 SQL 타입을 직접 지정
    private String content; // 내용 : "TEXT" 긴 본문 저장

    @Column(nullable = false, length = 100)
    private String author; // 작성자

    // 빌더 패턴을 위한 생성자
    public Post(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // 게시글 정보 업데이트 (Setter 대신 명시적 메서드 사용)
    // 객체의 불변성 유지
    // 의도된 상태 변경만 허용
    public void update(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
