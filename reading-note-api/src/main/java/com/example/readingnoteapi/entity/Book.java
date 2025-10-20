package com.example.readingnoteapi.entity;

import com.example.readingnoteapi.entity.enums.Genre;
import com.example.readingnoteapi.entity.enums.ReadingStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

/**
 *  독서 노트를 나타내는 JPA Entity
 *  내가 읽은 책들의 정보와 감상을 기록
 * **/
@Entity
@Table(name="books")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Book extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "책 제목은 필수입니다!")
    private String title; //책 제목

    @NotBlank(message = "저자명은 필수입니다!")
    @Size(max=100, message="저자명은 100자 이내로 입력해주세요.")
    @Column(nullable = false, length = 100)
    private String author; // 저자명

    @NotBlank(message = "독서 감상을 작성해주세요!")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String review; // 독서 감상문

    @Min(value=1, message = "별점은 1점 이상이어야 합니다.")
    @Max(value=5, message = "별점은 5점 이하여야 합니다.")
    @Column(nullable = false)
    private Integer rating; // 별점(1~5점)

    @Column(name="finished_date")
    private LocalDate finishedDate; // 독서 완료일

    @Enumerated(EnumType.STRING)
    private Genre genre; // 장르

    @Enumerated(EnumType.STRING)
    private ReadingStatus status; // 독서 상태
}
