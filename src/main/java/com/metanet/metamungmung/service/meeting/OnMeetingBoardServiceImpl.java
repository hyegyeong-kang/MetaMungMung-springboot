package com.metanet.metamungmung.service.meeting;

import com.metanet.metamungmung.dto.meeting.OnMeetingBoardDTO;
import com.metanet.metamungmung.dto.meeting.OnMeetingMemDTO;
import com.metanet.metamungmung.mapper.meeting.OnMeetingBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OnMeetingBoardServiceImpl implements OnMeetingBoardService{

    @Autowired
    private OnMeetingBoardMapper mapper;

    // 게시글 목록 조회

    // 게시글 상세 조회

    // 게시글 작성

    // 게시글 수정

    // 게시글 삭제

    // 게시글 검색
//=========================

    // 게시글 목록 조회
    @Override
    public List<OnMeetingBoardDTO> getBoardList(Long onMeetingIdx) {
        return mapper.getBoardList(onMeetingIdx);
    }

    // 온 모임 가입한 사용자 리스트 출력
    @Override
    public List<OnMeetingMemDTO> getOnMeetingMembers(Long onMeetingIdx) {
        return mapper.getOnMeetingMembers(onMeetingIdx);
    }
}
