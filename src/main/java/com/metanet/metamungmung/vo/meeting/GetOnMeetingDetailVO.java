package com.metanet.metamungmung.vo.meeting;

import lombok.Getter;

import java.util.Date;

@Getter
public class GetOnMeetingDetailVO {
    private Long memberIdx;
    private Long onMeetingIdx;
    private String title;
    private Date meetingDate;
    private int limit;
    private String contents;
    private Date createDate;
    private Date updateDate;
    private String status;
    private double latitude;
    private double longitude;
    private String locationAddress;
    private String startTime;
    private Long headcount;
}
