package com.shinhan.ex2.repository;

import com.shinhan.ex2.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//jpa는 인터페이스 선언만으로 자동으로 스프링의 빈으도 등록이됨
public interface MemoRepository extends JpaRepository<Memo,Long> {

    List<Memo> findByMnoBetweenOrderByMnoDesc(Long form, Long to);

    Page<Memo> findByMnoBetween(Long from, Long to , Pageable pageable);

    void deleteMemosByMnoLessThan(Long num);
}
