package com.shinhan.guestbook.entity;

import lombok.*;

import jakarta.persistence.*;

/*
 * ==========================================================
 * Guestbook Entity
 * ==========================================================
 * 방명록 정보를 저장하는 Entity
 *
 * BaseEntity를 상속받아
 * regDate(등록일), modDate(수정일)를 자동으로 관리한다.
 *
 * @Entity         JPA가 관리하는 엔티티
 * @Id             기본키(PK)
 * @GeneratedValue 기본키 자동 생성(AUTO_INCREMENT)
 * @Column         컬럼 속성 지정
 * @Builder        Builder 패턴 지원
 * @Getter         Getter 자동 생성
 * @NoArgsConstructor    기본 생성자 생성
 * @AllArgsConstructor   모든 필드를 사용하는 생성자 생성
 * @ToString       toString() 자동 생성
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Guestbook extends BaseEntity {

    /*방명록 번호(PK) : AUTO_INCREMENT 방식으로 자동 증가*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gno;
    /* 방명록 제목 : 최대 100자, NULL 허용 안 함*/
    @Column(length = 100, nullable = false)
    private String title;
    /* 방명록 내용: 최대 1500자 NULL 허용 안 함*/
    @Column(length = 1500, nullable = false)
    private String content;
    /* 작성자: 최대 50자 NULL 허용 안 함  */
    @Column(length = 50, nullable = false)
    private String writer;
    /* 제목 수정 */
    public void changeTitle(String title){
        this.title = title;
    }
    /* 내용 수정 */
    public void changeContent(String content){
        this.content = content;
    }
}