package com.metanet.metamungmung.vo.meeting;

import com.metanet.metamungmung.dto.meeting.OnMeetingMemDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class GetOnMeetingMemForOffVO {
    private Long onMeetingMemIdx;
    private Long onMeetingIdx;
    private List<GetOffMeeting2VO> offMeetingList;
}
