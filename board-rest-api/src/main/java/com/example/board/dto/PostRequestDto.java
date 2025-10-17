package com.example.board.dto;

import com.example.board.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// 게시글 데이터 전송 객체
// DTO: Entity와 분리해서 계층 간 데이터 전달 최적화
// PostRequestDto : 사용자가 입력할 정보만 (사용자에게 요청할 정보만)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRequestDto {

    private String title;
    private String content;
    private String author;

    // DTO -> Entity로 변환하는 메서드
    public Post toEntity(){
        return Post.builder()
                .title(this.title)
                .content(this.content)
                .author(this.author)
                .build();
    }
}
