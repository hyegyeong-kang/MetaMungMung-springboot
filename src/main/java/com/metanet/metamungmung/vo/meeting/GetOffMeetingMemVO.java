package com.metanet.metamungmung.vo.meeting;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
public class GetOffMeetingMemVO {
    private Long memberIdx;
    private String memberId;
    private String sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    private String memberImg;
}
