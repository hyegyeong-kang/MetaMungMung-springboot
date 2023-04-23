package com.metanet.metamungmung.mapper.meeting;

import com.metanet.metamungmung.dto.meeting.OnMeetingBoardReplyDTO;
import com.metanet.metamungmung.vo.meeting.GetOnMeetingBoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OnMeetingBoardReplyMapper {

    // 해당 게시물 댓글 조회
    public List<GetOnMeetingBoardVO> getBoardReply(Long onMeetingIdx, Long onMeetingBoardIdx, Long memberIdx);

    // 댓글 전체 조회
    public List<OnMeetingBoardReplyDTO> getAllReplyList(Long onMeetingIdx, Long memberIdx);

    // 해당 게시물 댓글 추가
    public void addReply(OnMeetingBoardReplyDTO replyDTO);

    // 해당 게시물 댓글 수정
    public void updateReply(Long onMeetingIdx, Long onMeetingBoardIdx, Long memberIdx);

    // 해당 게시물 댓글 삭제
    public void deleteReply(Long onMeetingIdx, Long onMeetingReplyIdx, Long memberIdx);

    // 댓글 전체 삭제
    public void deleteReplyAll(Long onMeetingIdx, Long onMeetingBoardIdx);

}

