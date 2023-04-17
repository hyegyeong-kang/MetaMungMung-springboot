package com.metanet.metamungmung.dto.meeting;

import lombok.Data;

import java.util.Date;

@Data
public class OnMeetingDTO {
    private Long onMeetingIdx;
    private String onMeetName;
    private String category;
    private String introduction;
    private String thumbnail;
    private String isPublic;
    private String onMeetingAddr;
    private Date createDate;
    private Date updateDate;
}
