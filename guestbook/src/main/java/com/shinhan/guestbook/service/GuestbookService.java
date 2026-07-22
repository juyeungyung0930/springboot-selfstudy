package com.shinhan.guestbook.service;

import com.shinhan.guestbook.dto.GuestbookDTO;
import com.shinhan.guestbook.dto.PageRequestDTO;
import com.shinhan.guestbook.dto.PageResultDTO;
import com.shinhan.guestbook.entity.Guestbook;

/*
 * ==========================================================
 * GuestbookService
 * ==========================================================
 * 방명록의 비즈니스 로직을 정의하는 인터페이스
 *
 * 구현 클래스(GuestbookServiceImpl)에서 실제 기능을 구현한다.
 */
public interface GuestbookService {
    /*
     * 방명록 등록
     * DTO를 전달받아 저장한 후 생성된 게시글 번호(gno)를 반환
     */
    Long register(GuestbookDTO dto);

    PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO);

    default Guestbook dtoToEntity(GuestbookDTO dto) {
        Guestbook entity = Guestbook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();
        return entity;

    }

    default GuestbookDTO entityToDto(Guestbook entity) {
        GuestbookDTO dto = GuestbookDTO.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
        return dto;
    }
}

