package com.example.question_board.dto.response;

import com.example.question_board.entity.Board;
import com.example.question_board.entity.status.BoardStatus;
import lombok.*;

/**
 * 게시판 응답 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardResponse {

    private Long id;
    private String boardName;
    private BoardStatus status;
    private String statusDescription;

    /** Entity → DTO 변환 */
    public static BoardResponse fromEntity(Board board) {
        return BoardResponse.builder()
                .id(board.getId())
                .boardName(board.getBoardName())
                .status(board.getStatus())
                .statusDescription(board.getStatus().getDescription())
                .build();
    }
}
