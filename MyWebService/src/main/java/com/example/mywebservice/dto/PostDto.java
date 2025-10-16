package com.example.mywebservice.dto;

import com.example.mywebservice.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 게시글 데이터 전송 객체
// DTO: Entity와 분리해서 계층 간 데이터 전달 최적화
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    // Entity -> DTO로 변환하는 정적 메서드
    public static PostDto from(Post post){
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getAuthor())
                .build();
    }

    // DTO -> Entity로 변환하는 메서드
    public Post toEntity(){
        return Post.builder()
                .title(this.title)
                .content(this.content)
                .author(this.author)
                .build();
    }
}
