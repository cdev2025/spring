package com.example.cat_wiki.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "고양이 등록/수정 요청 DTO")
public class CatRequestDto {
    @NotBlank(message = "고양이 이름은 필수 입력값입니다.")
    @Schema(description = "고양이 이름", example="Bella", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotBlank(message = "고양이 품종은 필수 입력값입니다.")
    @Schema(description = "고양이 품종", example="British Shorthair", requiredMode = Schema.RequiredMode.REQUIRED)
    private String breed;

    @Min(value = 0, message = "고양이 나이는 0살 이상이어야 합니다.")
    @NotNull(message = "고양이 나이는 필수 입력값입니다.")
    @Schema(description = "고양이 나이 (년 단위)", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    private int age;
}
