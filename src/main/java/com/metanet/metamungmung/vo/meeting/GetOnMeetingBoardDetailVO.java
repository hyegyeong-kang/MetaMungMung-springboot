package com.metanet.metamungmung.vo.meeting;

import lombok.Getter;

import java.util.Date;


@Getter
public class GetOnMeetingBoardDetailVO {
    private Long onMeetingBoardIdx;
    private Long onMeetingMemIdx;
    private Long onMeetingIdx;
    private Long memberIdx;
    private String boardWriter;
    private String boardContents;
    private String onMeetingBoardAddr;
  //  private Date boardCreateDate;
  //  private Date boardUpdateDate;
}
