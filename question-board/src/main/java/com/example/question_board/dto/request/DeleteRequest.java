package com.example.question_board.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteRequest {

    @NotNull(message = "ID는 필수입니다.")
    private Long id;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;
}
