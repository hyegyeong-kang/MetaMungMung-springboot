package com.metanet.metamungmung.dto.meeting;

import com.metanet.metamungmung.dto.member.MemberDTO;
import lombok.Data;

import java.util.Date;

@Data
public class OnMeetingMemDTO {
    private Long onMeetingMemIdx;
    private Long onMeetingIdx;
    private Long memberIdx;
    private Date joinDate;
    private String isHost;

    private MemberDTO memberDTO;
}
