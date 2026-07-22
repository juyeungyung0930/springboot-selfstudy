package com.shinhan.guestbook.service;


import com.shinhan.guestbook.dto.GuestbookDTO;
import com.shinhan.guestbook.dto.PageRequestDTO;
import com.shinhan.guestbook.dto.PageResultDTO;
import com.shinhan.guestbook.entity.Guestbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GuestbookServiceTests {

    @Autowired
    private GuestbookService service;

    @Test   // 테스트 메서드
    public void testRegister() {

        // 등록할 방명록 DTO 생성
        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
                .title("Sample Title...")
                .content("Sample Content...")
                .writer("user0")
                .build();

        // 서비스의 등록 기능 호출 후 생성된 gno 출력
        System.out.println(service.register(guestbookDTO));
/*
        Hibernate:
        insert
                into
        guestbook
                (content, moddate, regdate, title, writer)
        values
                (?, ?, ?, ?, ?)
*/


    }

    @Test
   public void testList(){

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();

       PageResultDTO<GuestbookDTO, Guestbook> resultDTO = service.getList(pageRequestDTO);

       for (GuestbookDTO guestbookDTO : resultDTO.getDtoList()) {
           System.out.println(guestbookDTO);
      }
    }



}
