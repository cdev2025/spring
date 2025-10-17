package com.example.board.dto;

import com.example.board.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// 게시글 데이터 전송 객체
// DTO: Entity와 분리해서 계층 간 데이터 전달 최적화
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    // JPA Auditing 필드 추가
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;

    // Entity -> DTO로 변환하는 정적 메서드
    public static PostResponseDto from(Post post){
        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getAuthor())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .createdBy(post.getCreatedBy())
                .updatedBy(post.getUpdatedBy())
                .build();
    }

//    // DTO -> Entity로 변환하는 메서드
//    public Post toEntity(){
//        return Post.builder()
//                .title(this.title)
//                .content(this.content)
//                .author(this.author)
//                .build();
//    }
}
