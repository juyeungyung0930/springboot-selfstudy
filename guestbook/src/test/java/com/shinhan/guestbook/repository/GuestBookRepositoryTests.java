package com.shinhan.guestbook.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.shinhan.guestbook.entity.Guestbook;
import com.shinhan.guestbook.entity.QGuestbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class GuestBookRepositoryTests {
    @Autowired
    private GuestbookRepository guestbookRepository;

    @Test
    public void insertDummis() {
        IntStream.rangeClosed(1, 300).forEach(i -> {
            Guestbook guestbook = Guestbook.builder()
                    .title("Tittle" + i)
                    .content("Content" + i)
                    .writer("user" + (i % 10))
                    .build();
            System.out.println(guestbookRepository.save(guestbook));
        });
    }

    @Test
    public void updateTest() {
        Optional<Guestbook> result = guestbookRepository.findById(300l);
        if (result.isPresent()) {
            Guestbook guestbook = result.get();
            guestbook.changeTitle("changeTitle");
            guestbook.changeContent("changeContent");
            guestbookRepository.save(guestbook);
        }
    }

    @Test
    public void testQuery1() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
/*import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
impor 잘해야함*/
        QGuestbook qGuestbook = QGuestbook.guestbook;
        String keyword = "1";
        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression expression = qGuestbook.title.contains(keyword);
        builder.and(expression);
        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);
            result.stream().forEach(guestbook->{
                System.out.println(guestbook);
        });
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
        where
        g1_0.title like ? escape '!'
        order by
        g1_0.gno desc
        limit
                ?, ?
        Hibernate:
        select
        count(g1_0.gno)
        from
        guestbook g1_0
        where
        g1_0.title like ? escape '!'

         */
    }

    @Test
    public void testQuery2() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

        QGuestbook qGuestbook = QGuestbook.guestbook;
        String keyword = "1";
        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression extitle= qGuestbook.title.contains(keyword);
        BooleanExpression excontent = qGuestbook.content.contains(keyword);
        BooleanExpression exAll=extitle.or(excontent);
        builder.and(exAll);
        builder.and(qGuestbook.gno.gt(0L));
        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);
        result.stream().forEach(guestbook->{
            System.out.println(guestbook);
        });
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
        where
                (
                        g1_0.title like ? escape '!'
        or g1_0.content like ? escape '!'
        )
        and g1_0.gno>?
                order by
        g1_0.gno desc
        limit
                ?, ?
        Hibernate:
        select
        count(g1_0.gno)
        from
        guestbook g1_0
        where
                (
                        g1_0.title like ? escape '!'
        or g1_0.content like ? escape '!'
        )
        and g1_0.gno>?
*/
    }
}
