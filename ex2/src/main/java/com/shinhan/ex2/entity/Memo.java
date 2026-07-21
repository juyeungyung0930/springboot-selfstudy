package com.shinhan.ex2.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="tbl_memo")
@ToString
@Getter@Builder@AllArgsConstructor@NoArgsConstructor
public class Memo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private long mno;

    @Column(length =200, nullable=false)
    private  String memoText;
}


/*
@Entity//해당클래스의 인스턴스들이 jpa로 관리 되는 엔테티 객체라는 것을 알려주는 어노테이션
@Table(name="tbl_memo") //테이블을 생성하는 어노테이션
@ToString
public class Memo {
    @Id //엔티티가 붙은 클래스는 pk에 해당하는 특정필드를 id로 지정
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    //사용자가 값을 사용하는 경우가 아니면 자동으로 생성되는 어노테이션
    */
/*
    * AUTO JPA 구현체가 생성방식을 결정(초기값)
    * IDENTITY 사용하는 데이터베이스가 키생성을 결정 mysql이나 마리아 디비인경유 auto increment사용
    * SEQUENCE 데이터베이스의 SEQUENCE를 이용해서 키를 생성
    * TABLE 키생성 전용테이블을 생성해서 키 생성(@TableGGenerated 와 함께사용)
    *//*

    private long mno;
}

*/
