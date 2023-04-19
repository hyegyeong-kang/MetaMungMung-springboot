package com.metanet.metamungmung.vo.meeting;

import com.metanet.metamungmung.dto.meeting.OnMeetingBoardReplyDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class GetOnMeetingBoardVO {
    private Long onMeetingBoardIdx;
    private Long onMeetingMemIdx;
    private Long onMeetingIdx;
    private Long memberIdx;
    private List<OnMeetingBoardReplyDTO> replyList;


}
