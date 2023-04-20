package com.metanet.metamungmung.service.meeting;

import com.metanet.metamungmung.dto.meeting.OnMeetingBoardDTO;
import com.metanet.metamungmung.dto.meeting.OnMeetingBoardReplyDTO;
import com.metanet.metamungmung.mapper.meeting.OnMeetingBoardReplyMapper;
import com.metanet.metamungmung.vo.meeting.GetOnMeetingBoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OnMeetingBoardReplyServiceImpl implements OnMeetingBoardReplyService{

    @Autowired
    private OnMeetingBoardReplyMapper mapper;

    // 해당 게시물 댓글 조회
    @Override
    public GetOnMeetingBoardVO replyList(Long onMeetingIdx, Long onMeetingBoardIdx, Long memberIdx) {
        return mapper.getBoardReply(onMeetingIdx, onMeetingBoardIdx, memberIdx);
    }


    // 해당 게시물 댓글 추가
    @Override
    public void addReply(Long onMeetingIdx, OnMeetingBoardReplyDTO replyDTO) {
        mapper.addReply(onMeetingIdx, replyDTO);
    }

    // 해당 게시물 댓글 수정
    @Override
    public void updateReply(Long onMeetingIdx, OnMeetingBoardReplyDTO replyDTO) {
        mapper.updateReply(onMeetingIdx, replyDTO);
    }

    // 해당 게시물 댓글 삭제
    @Override
    public void deleteReply(Long onMeetingIdx, Long onMeetingReplyIdx) {
        mapper.deleteReply(onMeetingIdx, onMeetingReplyIdx);
    }
}
