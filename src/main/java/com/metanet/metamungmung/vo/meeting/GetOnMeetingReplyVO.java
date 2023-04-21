package com.metanet.metamungmung.vo.meeting;

import lombok.Getter;

import java.util.Date;

@Getter
public class GetOnMeetingReplyVO {
    private Long onMeetingReplyIdx;
    private Long onMeetingBoardIdx;
    private Long onMeetingMemIdx;
    private Long onMeetingIdx;
    private Long memberIdx;
    private String replyContents;
    private Date replyCreateDate;
    private Date replyUpdateDate;
    private String replyWriter;
    private String replyWriterImg;
    private GetOnMeetingMemVO replyMember;
}
