package com.metanet.metamungmung.service.meeting;

import com.metanet.metamungmung.dto.meeting.OnMeetingBoardDTO;
import com.metanet.metamungmung.dto.meeting.OnMeetingBoardReplyDTO;
import com.metanet.metamungmung.mapper.meeting.OnMeetingBoardMapper;
import com.metanet.metamungmung.mapper.meeting.OnMeetingBoardReplyMapper;
import com.metanet.metamungmung.vo.meeting.GetOnMeetingBoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OnMeetingBoardReplyServiceImpl implements OnMeetingBoardReplyService{

    @Autowired
    private OnMeetingBoardReplyMapper mapper;

    @Autowired
    private OnMeetingBoardMapper boardMapper;

    // 해당 게시물 댓글 조회
    @Override
    public List<GetOnMeetingBoardVO> replyList(Long onMeetingIdx, Long onMeetingBoardIdx, Long memberIdx) {
        return mapper.getBoardReply(onMeetingIdx, onMeetingBoardIdx, memberIdx);
    }

    // 전체 댓글가져오기
    @Override
    public List<OnMeetingBoardReplyDTO> getAllReplyList(Long onMeetingIdx, Long memberIdx) {
        return mapper.getAllReplyList(onMeetingIdx, memberIdx);
    }


    // 해당 게시물 댓글 추가
    @Override
    public void addReply(OnMeetingBoardReplyDTO replyDTO) {
        mapper.addReply(replyDTO);
    }

    // 해당 게시물 댓글 수정
    @Override
    public void updateReply(Long onMeetingIdx, Long onMeetingBoardIdx, Long memberIdx) {
        mapper.updateReply(onMeetingIdx, onMeetingBoardIdx, memberIdx);
    }

    // 해당 게시물 댓글 삭제
    @Override
    public void deleteReply(Long onMeetingIdx, Long onMeetingReplyIdx, Long memberIdx) {
        mapper.deleteReply(onMeetingIdx, onMeetingReplyIdx, memberIdx);
    }

    // 해당 게시글 댓글 전체 삭제
    @Override
    public void deleteAll(Long onMeetingIdx, Long onMeetingBoardIdx) {
        mapper.deleteReplyAll(onMeetingIdx, onMeetingBoardIdx);
        boardMapper.deleteAllBoards(onMeetingIdx);
    }
}
