package com.metanet.metamungmung.dto.meeting;

import lombok.Data;

import java.util.Date;

@Data
public class OffMeetingDTO {
    private Long offMeetingIdx;
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
