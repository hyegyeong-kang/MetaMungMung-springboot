package com.metanet.metamungmung.dto.meeting;

import lombok.Data;

import java.util.Date;

@Data
public class OffMeetingMemDTO {
    private Long offMeetingMemIdx;
    private Long onMeetingIdx;
    private Long memberIdx;
    private Date createDate;
    private Date updateDate;
    private String status;
    private String isHost;
}
