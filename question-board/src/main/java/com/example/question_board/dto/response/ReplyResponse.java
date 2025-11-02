package com.example.question_board.dto.response;

import com.example.question_board.entity.Reply;
import com.example.question_board.entity.status.ReplyStatus;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 댓글 응답 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyResponse {

    private Long id;
    private Long postId;
    private String userName;
    private String title;
    private String content;
    private ReplyStatus status;
    private String statusDescription;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    /** Entity → DTO 변환 */
    public static ReplyResponse fromEntity(Reply reply) {
        return ReplyResponse.builder()
                .id(reply.getId())
                .postId(reply.getPostId())
                .userName(reply.getUserName())
                .title(reply.getTitle())
                .content(reply.getContent())
                .status(reply.getStatus())
                .statusDescription(reply.getStatus().getDescription())
                .createdAt(reply.getCreatedAt())
                .modifiedAt(reply.getModifiedAt())
                .build();
    }
}
