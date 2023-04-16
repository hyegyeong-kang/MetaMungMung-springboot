package com.metanet.metamungmung.dto.meeting;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class OffMeetingDTO {
    private Long offMeetingIdx;
    private Long memberIdx;
    private Long offMeetingMemIdx;
    private Long onMeetingIdx;
    private String title;
    private int limit;
    private String contents;
    private Date createDate;
    private Date updateDate;
    private String status;
    private double latitude;
    private double longitude;
    private String locationAddress;
    private Timestamp startTime;
    private Date meetingDate;
}
