package com.example.board.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * JPA Auditing을 적용한 기본 엔티티 클래스
 *
 * @MappedSuperclass : 이 클래스가 엔티티가 아니라 매핑 정보만 제공하는 수퍼 클래스이다
 * - 이 클래스를 상속받는 엔티티들이 이 클래스의 필드들을 자신의 테이블에 포함
 * - 실제로는 테이블이 생성되지 않음
 * - 상속받는 엔티티의 테이블에 컬럼으로 추가된다
 *
 * @EntityListeners(AuditingEntityListener.class) : JPA Entity Listener 등록
 * - AuditingEntityListener : Spring Data JPA에서 제공하는 Auditing 전용 리스너
 * - Entity 생명 주기 이벤트(@PrePersist, @PreUpdate 등)를 감지해서 Auditing 수행
 * **/
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseEntity {
    @CreatedDate // 엔티티가 처음 저장될 때 현재 시간을 자동으로 설정
    @Column(updatable = false) // 이 컬럼은 UPDATE 쿼리에서 제외됨.(생성 후 변경 불가)
    private LocalDateTime createdAt;  // 엔티티 생성 시간

    @LastModifiedDate // 엔티티가 수정될 때마다 현재 시간으로 자동으로 업데이트
    private LocalDateTime updatedAt; // 엔티티 최종 수정 시간

    @CreatedBy  // 엔티티를 생성한 사용자 정보를 자동으로 설정: AuditorAware<String> 빈에서 반환하는 값을 사용
    @Column(updatable = false)
    private String createdBy; // 엔티티 생성자

    @LastModifiedBy // 엔티티 마지막으로 수정한 사용자 정보 자동으로 설정 : AuditorAware<String> 빈에서 반환하는 값을 사용
    private String updatedBy; // 엔티티 최종 수정자
}
