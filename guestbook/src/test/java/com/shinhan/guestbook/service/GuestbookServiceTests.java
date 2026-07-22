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
/*
        Hibernate:
        select
        g1_0.gno,
                g1_0.content,
                g1_0.moddate,
                g1_0.regdate,
                g1_0.title,
                g1_0.writer
        from
        guestbook g1_0
        order by
        g1_0.gno desc
        limit
                ?, ?
        Hibernate:
        select
        count(g1_0.gno)
        from
        guestbook g1_0
        GuestbookDTO(gno=301, title=Sample Title..., content=Sample Content..., writer=user0, regDate=2026-07-22T09:24:51.812891, modDate=2026-07-22T09:24:51.812891)
        GuestbookDTO(gno=300, title=changeTitle, content=changeContent, writer=user0, regDate=2026-07-21T12:15:43.152318, modDate=2026-07-21T12:20:54.719436)
        GuestbookDTO(gno=299, title=Tittle299, content=Content299, writer=user9, regDate=2026-07-21T12:15:43.149317, modDate=2026-07-21T12:15:43.149317)
        GuestbookDTO(gno=298, title=Tittle298, content=Content298, writer=user8, regDate=2026-07-21T12:15:43.145655, modDate=2026-07-21T12:15:43.145655)
        GuestbookDTO(gno=297, title=Tittle297, content=Content297, writer=user7, regDate=2026-07-21T12:15:43.142645, modDate=2026-07-21T12:15:43.142645)
        GuestbookDTO(gno=296, title=Tittle296, content=Content296, writer=user6, regDate=2026-07-21T12:15:43.139508, modDate=2026-07-21T12:15:43.139508)
        GuestbookDTO(gno=295, title=Tittle295, content=Content295, writer=user5, regDate=2026-07-21T12:15:43.135512, modDate=2026-07-21T12:15:43.135512)
        GuestbookDTO(gno=294, title=Tittle294, content=Content294, writer=user4, regDate=2026-07-21T12:15:43.132505, modDate=2026-07-21T12:15:43.132505)
        GuestbookDTO(gno=293, title=Tittle293, content=Content293, writer=user3, regDate=2026-07-21T12:15:43.129505, modDate=2026-07-21T12:15:43.129505)
        GuestbookDTO(gno=292, title=Tittle292, content=Content292, writer=user2, regDate=2026-07-21T12:15:43.125506, modDate=2026-07-21T12:15:43.125506)
    */
    }



}
