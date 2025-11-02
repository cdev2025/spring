package com.example.question_board.entity.status;

import lombok.Getter;

/**
 * 게시판 상태
 * - 운영 중(활성), 비활성, 삭제, 관리자 검토 중
 */
@Getter
public enum BoardStatus {

    /** 정상적으로 운영 중인 게시판 */
    ACTIVE("정상 운영"),

    /** 임시로 중단된 게시판 (관리자 승인 대기 등) */
    INACTIVE("비활성 상태"),

    /** 관리자에 의해 삭제된 게시판 */
    DELETED("삭제됨"),

    /** 관리자 검토 또는 신고로 인해 잠시 중단 */
    PENDING("검토 중");

    private final String description;

    BoardStatus(String description) {
        this.description = description;
    }

}
