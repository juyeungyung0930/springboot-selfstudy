package com.shinhan.guestbook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {
    private  int page;
    private int size;
    public PageRequestDTO(){
        this.page=1; //페이지번호 기본값
        this.size=10; //
    }
    public Pageable getPageable(Sort sort){
        return PageRequest.of(page -1,size,sort);
        //페이지번호가 0부터시작한다는 점을 감안해서 1페이지의 경우 0이될수있도록 page -1형태로 작성
    }
}
