package com.example.question_board.entity.status;

import lombok.Getter;

/**
 * 댓글(답변) 상태
 * - 표시됨, 숨김, 삭제됨
 */
@Getter
public enum ReplyStatus {

    /** 정상적으로 표시되는 댓글 */
    VISIBLE("표시됨"),

    /** 관리자 또는 작성자에 의해 숨김 처리된 댓글 */
    HIDDEN("숨김"),

    /** 삭제된 댓글 */
    DELETED("삭제됨");

    private final String description;

    ReplyStatus(String description) {
        this.description = description;
    }
}
