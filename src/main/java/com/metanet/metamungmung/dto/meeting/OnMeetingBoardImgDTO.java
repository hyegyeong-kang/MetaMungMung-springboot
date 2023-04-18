package com.metanet.metamungmung.dto.meeting;


import lombok.Data;

@Data
public class OnMeetingBoardImgDTO {
    private Long onMeetingBoardImgIdx;
    private Long onMeetingBoardIdx;
    private String fileName;
    private String uploadPath;
}
