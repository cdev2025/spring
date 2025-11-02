package com.example.question_board.entity;

import com.example.question_board.entity.status.ReplyStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "replies")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Reply extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

/*    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id", nullable = false)
    private Post post;*/
    @Column(name = "post_id", nullable = false)
    private Long postId;

    @Column(name = "user_name", nullable = false, length = 50)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private ReplyStatus status;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(columnDefinition ="TEXT")
    private String content;

    /**
     * 댓글 상태 변경 (Soft Delete 등)
     */
    public void changeStatus(ReplyStatus status) {
        this.status = status;
    }

    /**
     * 댓글 수정
     */
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
