package com.metanet.metamungmung.vo.meeting;

import com.metanet.metamungmung.dto.meeting.OnMeetingBoardReplyDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class GetOnMeetingBoard2VO {
    private Long onMeetingBoardIdx;
    private Long onMeetingMemIdx;
    private Long onMeetingIdx;
    private List<OnMeetingBoardReplyDTO> replyList;
}
