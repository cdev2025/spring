package com.example.readingnoteapi.dto;

import com.example.readingnoteapi.entity.enums.Genre;
import com.example.readingnoteapi.entity.enums.ReadingStatus;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 독서 노트 관련 DTO 클래스들
 * Java Record 사용해서 간결하게 정의
 * **/
public class BookDto {
    // 책 등록/수정 요청 DTO
    public record BookRequest(
            @NotBlank(message = "책 제목을 입력해주세요!")
            @Size(max=200, message="제목은 200자 이내로 입력해주세요.")
            String title,

            @NotBlank
            @Size(max=100)
            String author,

            @NotBlank
            String review,

            @NotNull
            @Min(value = 1)
            @Max(value = 5)
            Integer rating,


            LocalDate finishedDate,
            Genre genre,
            ReadingStatus status
    ){}

    // 책 정보 응답 DTO
    public record BookResponse(
            Long id,
            String title,
            String author,
            String review,
            Integer rating,
            LocalDate finishedDate,
            Genre genre,
            ReadingStatus status,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ){}
}
