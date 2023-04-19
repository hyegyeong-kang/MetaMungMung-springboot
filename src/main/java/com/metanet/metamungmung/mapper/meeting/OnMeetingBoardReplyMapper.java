package com.metanet.metamungmung.mapper.meeting;

import com.metanet.metamungmung.dto.meeting.OnMeetingBoardReplyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OnMeetingBoardReplyMapper {

    // 해당 게시물 댓글 조회
    public List<OnMeetingBoardReplyDTO> replyList(Long onMeetingIdx);

    // 해당 게시물 댓글 추가
    public void addReply(Long onMeetingIdx, OnMeetingBoardReplyDTO replyDTO);

    // 해당 게시물 댓글 수정
    public void updateReply(Long onMeetingIdx, OnMeetingBoardReplyDTO replyDTO);

    // 해당 게시물 댓글 삭제
    public void deleteReply(Long onMeetingIdx, Long onMeetingReplyIdx);


}
