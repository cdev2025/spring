package com.example.question_board.dto.response;

import com.example.question_board.entity.Post;
import com.example.question_board.entity.status.PostStatus;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 게시글 응답 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponse {

    private Long id;
    private Long boardId;
    private String userName;
    private String email;
    private String title;
    private String content;
    private PostStatus status;
    private String statusDescription;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    /** Entity → DTO 변환 */
    public static PostResponse fromEntity(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .boardId(post.getBoardId())
                .userName(post.getUserName())
                .email(post.getEmail())
                .title(post.getTitle())
                .content(post.getContent())
                .status(post.getStatus())
                .statusDescription(post.getStatus().getDescription())
                .createdAt(post.getCreatedAt())
                .modifiedAt(post.getModifiedAt())
                .build();
    }
}
