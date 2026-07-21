package com.shinhan.ex2.repository;

import com.shinhan.ex2.entity.Memo;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemoRepositoryTests {

    @Autowired
    MemoRepository memoRepository;

    @Test
    //100개씩 삽입
    public void testClass() {
        System.out.println(memoRepository.getClass().getName());
        //100번 돌리는 반복문
        IntStream.rangeClosed(1, 100).forEach(i -> {

            Memo memo = Memo.builder().memoText("Smaple..." + i).build();
            memoRepository.save(memo);
        });
    }
/*
| 구분               | findById()             | getOne() / getReferenceById()                       |
| ---------------   | ----------------------- | -------------------------------------------------- |
| SQL 실행           | **바로 실행**             | **실제 사용할 때 실행(지연 로딩)**                     |
| 반환 타입           | `Optional<Memo>`        | `Memo`(Proxy 객체)                                 |
| 데이터 없을 때       | `Optional.empty()`      | 사용할 때 예외 발생 가능                              |
| @Transactional     | 필요 없음                | **필요**(지연 로딩 때문)                              |
| 현재 권장 여부       | ✅ 많이 사용            | ⚠️ `getOne()`은 deprecated, `getReferenceById()` 사용 |

실무에서는 대부분 findById()를 많이 사용합니다.
반면 **getReferenceById()(예전의 getOne())는
연관관계를 설정할 때처럼 실제 데이터를 바로 읽지 않고 ID만 필요할 때 주로 사용합니다.

예를 들어 Order를 저장하면서 Member의 ID만 연결하면 되는 경우에는
getReferenceById()가 유용하고, 화면에 회원 정보를 보여주거나
값을 읽어야 하는 경우에는 findById()를 사용하는 것이 일반적입니다.
 */

    @Test

    public void textSelect() {
        System.out.println("===== textSelect1 시작 =====");
        //데이터베이스에 존재하는 mno
        Long mno = 100L;
        //findById의 경우 Optional타입으로 반횐되기 떄문에 한번더 결과가 존재히는지를 체크하는 형태로 작성하게됨
        Optional<Memo> result = memoRepository.findById(mno);

        System.out.println("=================");
        if (result.isPresent()) {
            Memo memo = result.get();
            System.out.println(memo);
            System.out.println("===== textSelect1 종료 =====");
        }


/*       콘솔에 실행되는 결과
===== textSelect1 시작 =====
Hibernate:
    select
        m1_0.mno,
        m1_0.memo_text
    from
        tbl_memo m1_0
    where
        m1_0.mno=?
=================
Memo(mno=100, memoText=Smaple...100)
===== textSelect1 종료 =====
*/
    }

    @Test
    @Transactional
    public void textSelect2() {
        System.out.println("===== textSelect2 시작 =====");
        //데이터베이스에 존재하는 mno
        Long mno = 100L;
        Memo memo = memoRepository.getOne(mno);
        System.out.println("=================");
        System.out.println(memo);

        System.out.println("===== textSelect2 끝 =====");
/*       콘솔에 실행되는 결과
       ===== textSelect2 시작 =====
        =================
        Hibernate:
        select
        m1_0.mno,
                m1_0.memo_text
        from
        tbl_memo m1_0
        where
        m1_0.mno=?
                Memo(mno=100, memoText=Smaple...100)
        ===== textSelect2 끝 =====
*/
    }

    @Test
    public void testUpdate() {
        Memo memo = Memo.builder().mno(100L).memoText("Update text").build();
        System.out.println(memoRepository.save(memo));
        /*
        Hibernate:
          select
                m1_0.mno,
                m1_0.memo_text
          from
                tbl_memo m1_0
          where
                m1_0.mno=?
        Hibernate:
            update
                tbl_memo
            set
                memo_text=?
            where
                mno=?
        Memo(mno=100, memoText=Update text)
        */
    }

    @Test
    public void testDelete() {
        long mno = 1L;
        memoRepository.deleteById(mno);
        System.out.println(mno);
        /*
        Hibernate:
        select
        m1_0.mno,
                m1_0.memo_text
        from
        tbl_memo m1_0
        where
        m1_0.mno=?
        */
    }

    @Test
    //해당기능은 프론트와 연결하여 사용할떄 사용함 예시로는 게시판10개씩보여줄때 그렇게 사용될수있음
    public void testPageDefault() {
        //1페이지에 10개
        //import시에 .data관련 클래스들을 사용하도록 주의 해ㅎ
        Pageable pageable = PageRequest.of(0, 10);
        Page<Memo> result = memoRepository.findAll(pageable);
        System.out.println("-------------------------");
        System.out.println("Total Page    : " + result.getTotalPages());
        System.out.println("Total Count   : " + result.getTotalElements());
        System.out.println("Page Number   : " + result.getNumber());
        System.out.println("Page Size     : " + result.getSize());
        System.out.println("has next Page : " + result.hasNext());
        System.out.println("first Page    : " + result.isFirst());
        System.out.println("-------------------------");

/*      -------------------------
        Total Page : 60
        Total Count : 598
        Page Number : 0
        Page Size : 10
        has next Page : true
        first Page : true
        -------------------------
*/
        for (Memo memo : result.getContent()) {
            System.out.println(memo);
        }
        System.out.println("-------------------------");
    }
/*    -------------------------

    Memo(mno=2, memoText=Smaple...2)

    Memo(mno=3, memoText=Smaple...3)

    Memo(mno=4, memoText=Smaple...4)

    Memo(mno=5, memoText=Smaple...5)

    Memo(mno=6, memoText=Smaple...6)

    Memo(mno=7, memoText=Smaple...7)

    Memo(mno=8, memoText=Smaple...8)

    Memo(mno=9, memoText=Smaple...9)

    Memo(mno=10, memoText=Smaple...10)

    Memo(mno=11, memoText=Smaple...11)
    -------------------------
    */

    @Test
    public void testSort() {
        Sort sort1 = Sort.by("mno").descending();
        Sort sort2 = Sort.by("mno").ascending();
        Sort sortAll = sort1.and(sort2);
        Pageable pageable2 = PageRequest.of(0,10,sortAll);
        Pageable pageable = PageRequest.of(0,10,sort1);
        Page<Memo> result= memoRepository.findAll(pageable);
        result.get().forEach(memo -> {
            System.out.println(memo);
        });

     /*
       Hibernate:
        select
        m1_0.mno,
                m1_0.memo_text
        from
        tbl_memo m1_0
        order by
        m1_0.mno desc
        limit
                ?, ?
        Hibernate:
        select
        count(m1_0.mno)
        from
        tbl_memo m1_0
        Memo(mno=601, memoText=Smaple...100)
        Memo(mno=600, memoText=Smaple...99)
        Memo(mno=599, memoText=Smaple...98)
        Memo(mno=598, memoText=Smaple...97)
        Memo(mno=597, memoText=Smaple...96)
        Memo(mno=596, memoText=Smaple...95)
        Memo(mno=595, memoText=Smaple...94)
        Memo(mno=594, memoText=Smaple...93)
        Memo(mno=593, memoText=Smaple...92)
        Memo(mno=592, memoText=Smaple...91)
        */
    }
    @Test
    public void testQueryMethods(){
        List<Memo> list = memoRepository.findByMnoBetweenOrderByMnoDesc(70l,80l);
        for(Memo memo :  list){
            System.out.println(memo);
        }
/*
    Hibernate:
        select
        m1_0.mno,
                m1_0.memo_text
        from
        tbl_memo m1_0
        where
        m1_0.mno between ? and ?
                order by
        m1_0.mno desc
        Memo(mno=80, memoText=Smaple...80)
        Memo(mno=79, memoText=Smaple...79)
        Memo(mno=78, memoText=Smaple...78)
        Memo(mno=77, memoText=Smaple...77)
        Memo(mno=76, memoText=Smaple...76)
        Memo(mno=75, memoText=Smaple...75)
        Memo(mno=74, memoText=Smaple...74)
        Memo(mno=73, memoText=Smaple...73)
        Memo(mno=72, memoText=Smaple...72)
        Memo(mno=71, memoText=Smaple...71)
        Memo(mno=70, memoText=Smaple...70)
        */
    }

    @Test
    public void testQueryMethodWithPageable(){
        Pageable pageable= PageRequest.of(0,10, Sort.by("mno").descending());
        Page<Memo> result= memoRepository.findByMnoBetween(10L, 50L, pageable);
        result.get().forEach(memo-> System.out.println(memo));

/*
        Hibernate:
        select
        m1_0.mno,
                m1_0.memo_text
        from
        tbl_memo m1_0
        where
        m1_0.mno between ? and ?
                order by
        m1_0.mno desc
        limit
                ?
                Hibernate:
                select
        count(m1_0.mno)
        from
        tbl_memo m1_0
        where
        m1_0.mno between ? and ?
                Memo(mno=50, memoText=Smaple...50)
        Memo(mno=49, memoText=Smaple...49)
        Memo(mno=48, memoText=Smaple...48)
        Memo(mno=47, memoText=Smaple...47)
        Memo(mno=46, memoText=Smaple...46)
        Memo(mno=45, memoText=Smaple...45)
        Memo(mno=44, memoText=Smaple...44)
        Memo(mno=43, memoText=Smaple...43)
        Memo(mno=42, memoText=Smaple...42)
        Memo(mno=41, memoText=Smaple...41)
*/
    }
    //최종결과를 커밋하기위해사용 적용하지 않으면 롤백처리되어 결과반영되지않음
    @Commit
    //select문과 삭제문이 동시에 작업되기 떄무에 없으면 오류발생
    @Transactional
    @Test
    public void testDeleteQueryMethod(){
        //xxL xx개까지 데이터 삭제됨
        memoRepository.deleteMemosByMnoLessThan(50L);
    }
}