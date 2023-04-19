package com.metanet.metamungmung.dto.meeting;

import com.metanet.metamungmung.dto.member.MemberDTO;
import lombok.Data;

import java.util.Date;

@Data
public class OffMeetingMemDTO {
    private Long offMeetingMemIdx;
    private Long onMeetingMemIdx;
    private Long onMeetingIdx;
    private Long offMeetingIdx;
    private Long memberIdx;
    private Date createDate;
    private Date updateDate;
    private String status;
    private String isHost;
}
