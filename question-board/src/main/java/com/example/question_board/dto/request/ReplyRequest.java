package com.example.question_board.dto.request;

import com.example.question_board.entity.Reply;
import com.example.question_board.entity.status.ReplyStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * 댓글 작성 요청 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyRequest {

    @NotBlank(message = "작성자 이름은 필수입니다.")
    private String userName;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 4, max = 20, message = "비밀번호는 4자 이상 20자 이하로 입력해주세요.")
    private String password;

    @NotBlank(message = "제목은 필수입니다.")
    private String title;

    private String content;

    /** DTO → Entity 변환 */
    public Reply toEntity(Long postId) {
        return Reply.builder()
                .postId(postId)
                .userName(this.userName)
                .password(this.password)
                .title(this.title)
                .content(this.content)
                .status(ReplyStatus.VISIBLE)
                .build();
    }
}
