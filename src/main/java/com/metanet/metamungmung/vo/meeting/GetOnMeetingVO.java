package com.metanet.metamungmung.vo.meeting;

import com.metanet.metamungmung.dto.meeting.OnMeetingBoardDTO;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class GetOnMeetingVO {
    private Long onMeetingIdx;
    private String onMeetName;
    private Date createDate;
    private Date updateDate;
    private String introduction;
    private String thumbnail;
    private int personnel;

    private List<GetOnMeetingBoardVO> boardList;
}
