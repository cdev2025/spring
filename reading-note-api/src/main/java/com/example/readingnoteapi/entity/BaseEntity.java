package com.example.readingnoteapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// 모든 엔티티의 공통 시간 필드를 관리
// JPA Auditing 활용 생성/수정 시간을 자동으로 관리
@MappedSuperclass                              // 테이블은 만들어지지 않고, 지식 엔티티에게 매핑 정보만 제공
@EntityListeners(AuditingEntityListener.class) // JPA Auditing 리스너 등록
@Getter
public class BaseEntity {

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
