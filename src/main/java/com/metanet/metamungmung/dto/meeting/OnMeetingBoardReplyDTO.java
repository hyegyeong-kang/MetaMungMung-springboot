package com.metanet.metamungmung.dto.meeting;

import lombok.Data;

import java.util.Date;

@Data
public class OnMeetingBoardReplyDTO {
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
}
