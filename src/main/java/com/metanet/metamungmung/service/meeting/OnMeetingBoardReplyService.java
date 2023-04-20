package com.metanet.metamungmung.service.meeting;

import com.metanet.metamungmung.dto.meeting.OnMeetingBoardReplyDTO;

import java.util.List;

public interface OnMeetingBoardReplyService {

    // 해당 게시물 댓글 조회
    public List<OnMeetingBoardReplyDTO> replyList(Long onMeetingIdx);

    // 해당 게시물 댓글 추가
    public void addReply(Long onMeetingIdx, OnMeetingBoardReplyDTO replyDTO);

    // 해당 게시물 댓글 수정
    public void updateReply(Long onMeetingIdx, OnMeetingBoardReplyDTO replyDTO);

    // 해당 게시물 댓글 삭제
    public void deleteReply(Long onMeetingIdx, Long onMeetingReplyIdx);
}
