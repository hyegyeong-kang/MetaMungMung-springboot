package com.metanet.metamungmung.dto.meeting;

import lombok.Data;

import java.util.Date;

@Data
public class PatchOffMeetingDTO {
    private Long offMeetingIdx;
    private String title;
    private Date meetingDate;
    private int limit;
    private String contents;
    private Date updateDate;
    private String startTime;
}
