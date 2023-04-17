package com.metanet.metamungmung.dto.meeting;

import lombok.Data;

import java.util.Date;

@Data
public class OnMeetingMemDTO {
    private Long onMeetingMemIdx;
    private Long onMeetingIdx;
    private Long memberIdx;
    private Date joinDate;
    private String isHost;
}
