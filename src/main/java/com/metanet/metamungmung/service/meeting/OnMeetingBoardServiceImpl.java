package com.metanet.metamungmung.service.meeting;

import com.metanet.metamungmung.dto.meeting.OnMeetingBoardDTO;
import com.metanet.metamungmung.dto.meeting.OnMeetingMemDTO;
import com.metanet.metamungmung.mapper.meeting.OnMeetingBoardMapper;
import com.metanet.metamungmung.vo.meeting.GetOnMeetingBoardVO;
import com.metanet.metamungmung.vo.meeting.GetOnMeetingVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OnMeetingBoardServiceImpl implements OnMeetingBoardService{

    @Autowired
    private OnMeetingBoardMapper mapper;

    // 게시글 목록 조회
    @Override
    public List<GetOnMeetingBoardVO> getBoardList(Long onMeetingIdx) {
        return mapper.getBoardList(onMeetingIdx);
    }

    // 온 모임 가입한 사용자 리스트 출력
    @Override
    public List<OnMeetingMemDTO> getOnMeetingMembers(Long onMeetingIdx) {
        return mapper.getOnMeetingMembers(onMeetingIdx);
    }

    // 게시글 작성
    @Override
    public String registerBoard(OnMeetingBoardDTO board) {
        return mapper.registerBoard(board);
    }

    // 게시글 수정
    @Override
    public String updateBoard(OnMeetingBoardDTO board) {
        return mapper.updateBoard(board);
    }

    // 게시글 삭제
    @Override
    public String delete(Long onMeetingIdx, Long onMeetingBoardIdx) {
        return mapper.deleteBoard(onMeetingIdx, onMeetingBoardIdx);
    }
}
