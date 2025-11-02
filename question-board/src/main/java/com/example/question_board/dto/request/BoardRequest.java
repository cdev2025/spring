package com.example.question_board.dto.request;

import com.example.question_board.entity.Board;
import com.example.question_board.entity.status.BoardStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * 게시판 생성 요청 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardRequest {

    @NotBlank(message = "게시판 이름은 필수입니다.")
    private String boardName;

    /** DTO → Entity 변환 */
    public Board toEntity() {
        return Board.builder()
                .boardName(this.boardName)
                .status(BoardStatus.ACTIVE)
                .build();
    }
}
