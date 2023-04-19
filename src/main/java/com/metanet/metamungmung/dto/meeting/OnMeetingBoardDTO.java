package com.metanet.metamungmung.dto.meeting;

import com.metanet.metamungmung.dto.member.MemberDTO;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OnMeetingBoardDTO {
    private Long onMeetingBoardIdx;
    private Long onMeetingMemIdx;
    private Long onMeetingIdx;
    private Long memberIdx;
    private String writer;
    private String contents;
    private String onMeetingBoardAddr;
    private Date createDate;
    private Date updateDate;
    private MemberDTO onMeetingMember;
    //private List<OnMeetingBoardReplyDTO> boardReplyList;
    //private List<OnMeetingBoardImgDTO> BoardImageList;
}
