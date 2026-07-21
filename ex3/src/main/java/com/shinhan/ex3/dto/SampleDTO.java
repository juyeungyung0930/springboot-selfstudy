package com.shinhan.ex3.dto;
import java.time.format.DateTimeFormatter;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

//getter, setter, tostring, equals, hashcode을 자동생성하는 롬복의 어노테이션
@Data
@Builder(toBuilder = true)
public class SampleDTO {
    private  Long sno;
    private String first;
    private String last;
    private LocalDateTime regTime;
}
