package com.example.readingnoteapi.entity.enums;

// 책 장르 열거형
public enum Genre {
    FICTION("소설"),
    NON_FICTION("비소설"),
    SCIENCE("과학"),
    THECHNOLOGY("기술"),
    BUSINESS("비즈니스"),
    SELF_HELP("자기계발"),
    HISTORY("역사"),
    BIOGRAPHY("전기"),
    PHILOSOPHY("철학"),
    OTHER("기타");

    private final String displayName;

    Genre(String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
