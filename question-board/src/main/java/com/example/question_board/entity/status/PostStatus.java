package com.example.question_board.entity.status;

import lombok.Getter;

/**
 * 게시글 상태
 * - 공개, 비공개, 삭제, 신고 블라인드
 */
@Getter
public enum PostStatus {

    /** 누구나 볼 수 있는 일반 공개 상태 */
    PUBLIC("공개"),

    /** 작성자 본인만 볼 수 있는 비공개 상태 */
    PRIVATE("비공개"),

    /** 작성자 또는 관리자에 의해 삭제된 상태 */
    DELETED("삭제됨"),

    /** 신고 누적으로 관리자에 의해 블라인드 처리된 상태 */
    BLOCKED("신고로 숨김");

    private final String description;

    PostStatus(String description) {
        this.description = description;
    }

}
