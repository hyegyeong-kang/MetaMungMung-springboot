package com.metanet.metamungmung.vo.meeting;

import com.metanet.metamungmung.dto.meeting.OnMeetingBoardReplyDTO;
import com.metanet.metamungmung.dto.member.MemberDTO;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class GetOnMeetingBoardVO {
    private Long onMeetingBoardIdx;
    private Long onMeetingMemIdx;
    private Long onMeetingIdx;
    private Long memberIdx;
    private String boardWriter;
    private String boardContents;
    private Date boardCreateDate;
    private Date boardUpdateDate;
    private MemberDTO boardMember;
    private List<GetOnMeetingReplyVO> replyList;
}
