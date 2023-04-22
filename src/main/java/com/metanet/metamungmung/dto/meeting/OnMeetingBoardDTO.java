package com.metanet.metamungmung.dto.meeting;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OnMeetingBoardDTO {
    private Long onMeetingBoardIdx;
    private Long onMeetingMemIdx;
    private Long onMeetingIdx;
    private Long MemberIdx;
    private String boardWriter;
    private String boardContents;
    private String onMeetingBoardAddr;
    private Date createDate;
    private Date updateDate;
    //private List<OnMeetingBoardReplyDTO> boardReplyList;
   // private List<OnMeetingBoardImgDTO> BoardImageList;
}
