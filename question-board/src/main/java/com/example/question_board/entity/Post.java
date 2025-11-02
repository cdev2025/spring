package com.example.question_board.entity;

import com.example.question_board.entity.status.PostStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Post extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 연관관계 : Post(N) : Board (1)
/*    @ManyToOne(fetch = FetchType.LAZY) // N:1 관계, 지연로딩
    @JoinColumn(name = "board_id", nullable = false) // 외래키 컬럼명: board_id
    private Board board; */
    @Column(name = "board_id", nullable = false)
    private Long boardId;

    @Column(name = "user_name", nullable = false, length = 50)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PostStatus status;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(columnDefinition ="TEXT")
    private String content;

    /**
     * 게시글 상태 변경 (Soft Delete 등)
     */
    public void changeStatus(PostStatus status) {
        this.status = status;
    }

    /**
     * 게시글 수정 (제목 + 내용 변경)
     */
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
