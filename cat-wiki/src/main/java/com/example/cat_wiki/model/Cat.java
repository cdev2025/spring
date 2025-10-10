package com.example.cat_wiki.model;

import lombok.*;

// 고양이 정보 담는 모델
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cat {
    private Long id;
    private String name;
    private String breed;
    private int age;
}
