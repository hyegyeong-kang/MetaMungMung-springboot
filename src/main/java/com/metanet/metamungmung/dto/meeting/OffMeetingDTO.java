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
    private Date meetingDate;
    private int limit;
    private String contents;
    private Date createDate;
    private Date updateDate;
    private double latitude;
    private double longitude;
    private String locationAddress;
    private String startTime;
    private String status;
}
