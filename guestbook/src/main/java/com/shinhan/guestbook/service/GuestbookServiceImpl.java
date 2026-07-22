package com.shinhan.guestbook.service;

import com.shinhan.guestbook.dto.GuestbookDTO;
import com.shinhan.guestbook.entity.Guestbook;
import com.shinhan.guestbook.repository.GuestbookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/*
 * ==========================================================
 * GuestbookServiceImpl
 * ==========================================================
 * GuestbookService를 구현하는 서비스 클래스
 *
 * 실제 비즈니스 로직을 처리하며,
 * Controller와 Repository를 연결하는 역할을 한다.
 *
 * @Service  서비스 계층의 Bean으로 등록
 * @Log4j2   로그 출력 기능 제공
 */
@Service
@Log4j2
@RequiredArgsConstructor //의존성 자동주입
public class GuestbookServiceImpl implements GuestbookService {

    private final GuestbookRepository repository; //반드시 final로선언해야함
    /*
     * 방명록 등록
     * 현재는 구현 전이므로 null을 반환
     * 이후 DTO → Entity 변환 후 DB에 저장할 예정
     */
    @Override
    public Long register(GuestbookDTO dto) {

        log.info("DTO~~~~~~~~~~~~~");
        log.info(dto);

        Guestbook entity=dtoToEntity(dto);

        log.info(entity);
        repository.save(entity);

        return entity.getGno();
    }
}