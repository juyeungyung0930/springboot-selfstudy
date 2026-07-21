package com.shinhan.guestbook.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

import lombok.Getter;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/*
 * ==========================================================
 * BaseEntity
 * ==========================================================
 * 모든 Entity가 공통적으로 사용할 컬럼을 정의하는 부모 클래스
 * GuestBook 테이블에는 자동으로
 * 1. regDate : 최초 생성 시간
 * 2. modDate : 마지막 수정 시간
 * 컬럼이 생성된다.
@MappedSuperclass	부모 클래스의 필드를 자식 Entity에 상속
@EntityListeners(AuditingEntityListener.class)	저장/수정 이벤트를 감지
@CreatedDate	최초 저장 시간을 자동 입력
@LastModifiedDate	수정 시간을 자동 입력
@Column	데이터베이스 컬럼 정보 지정
@Getter	Lombok이 Getter 메서드를 자동 생성
@EnableJpaAuditing
Auditing 기능 활성화 (필수)
*/
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseEntity {
    /**
     * updatable = false
     * → 최초 저장 이후에는 절대 수정되지 않는다.
     */
    @CreatedDate
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;
    /**
     * 엔티티가 수정될 때마다 현재 시간을 자동 저장
     */
    @LastModifiedDate
    @Column(name = "moddate")
    private LocalDateTime modDate;
}