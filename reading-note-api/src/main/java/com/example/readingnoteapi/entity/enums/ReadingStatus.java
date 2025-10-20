package com.example.readingnoteapi.entity.enums;

// 독서 상태 열거형
public enum ReadingStatus {
    READING("읽는 중"),
    COMPLETED("완독"),
    PAUSED("일시정지"),
    DROPPED("중단");

    private final String displayName;

    ReadingStatus(String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName(){
        return displayName;
    }
}
