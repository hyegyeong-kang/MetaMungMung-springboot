package com.metanet.metamungmung.service.meeting;

import com.metanet.metamungmung.dto.meeting.OnMeetingBoardDTO;
import com.metanet.metamungmung.dto.meeting.OnMeetingMemDTO;
import com.metanet.metamungmung.vo.meeting.GetOnMeetingBoardVO;
import com.metanet.metamungmung.vo.meeting.GetOnMeetingVO;

import java.util.List;

public interface OnMeetingBoardService {

    // 게시글 목록 조회
    public List<GetOnMeetingVO> getBoardList(Long onMeetingIdx);

    // 온모임 가입한 사용자 리스트
    public List<OnMeetingMemDTO> getOnMeetingMembers(Long onMeetingIdx);

    // 온모임 가입한 사용자 전체인원 수
    public String getCnt(Long onMeetingIdx);

    // 게시글 상세 조회

    // 게시글 작성
    public void registerBoard(OnMeetingBoardDTO board);

    // 게시글 수정
    public String updateBoard(OnMeetingBoardDTO board);

    // 한개의 게시글 삭제 -> 댓글 함께 삭제
    public void delete(Long onMeetingIdx, Long onMeetingBoardIdx);

    // 게시글 전체 삭제
    public void deleteAllBoards(Long onMeetingIdx);

    // 게시글 검색
    public List<GetOnMeetingVO> getSearchBoards(Long onMeetingIdx,String keyword);

}
