package com.metanet.metamungmung.vo.meeting;

import lombok.Getter;

import java.util.List;

@Getter
public class GetOffMeetingVO {
    private Long offMeetingIdx;
    private List<GetOffMeetingMemVO> offMeetingMembers;
}
