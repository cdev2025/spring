package com.example.question_board.dto.request;

import com.example.question_board.entity.Post;
import com.example.question_board.entity.status.PostStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * 게시글 작성 요청 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRequest {

    @NotBlank(message = "작성자 이름은 필수입니다.")
    private String userName;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 4, max = 20, message = "비밀번호는 4자 이상 20자 이하로 입력해주세요.")
    private String password;

    @Email(message = "유효한 이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "제목은 필수입니다.")
    private String title;

    private String content;

    /** DTO → Entity 변환 */
    public Post toEntity(Long boardId) {
        return Post.builder()
                .boardId(boardId)
                .userName(this.userName)
                .password(this.password)
                .email(this.email)
                .title(this.title)
                .content(this.content)
                .status(PostStatus.PUBLIC)
                .build();
    }
}
