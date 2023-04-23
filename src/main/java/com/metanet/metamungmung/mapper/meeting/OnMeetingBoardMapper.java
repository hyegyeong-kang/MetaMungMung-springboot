package com.metanet.metamungmung.mapper.meeting;


import com.metanet.metamungmung.dto.meeting.OffMeetingMemDTO;
import com.metanet.metamungmung.dto.meeting.OnMeetingBoardDTO;
import com.metanet.metamungmung.dto.meeting.OnMeetingMemDTO;
import com.metanet.metamungmung.vo.meeting.GetOnMeetingBoardVO;
import com.metanet.metamungmung.vo.meeting.GetOnMeetingVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OnMeetingBoardMapper {
    // 게시글 목록 조회
    public List<GetOnMeetingVO> getBoardList(Long onMeetingIdx);

    // 온 모임 가입한 사람 리스트
    public List<OnMeetingMemDTO> getOnMeetingMembers(Long onMeetingIdx);


    public String getCnt(Long onMeetingIdx);
    // 게시글 상세 조회


    // 게시글 작성
    public void registerBoard(OnMeetingBoardDTO board);

    // 게시글 수정
    public String updateBoard(OnMeetingBoardDTO board);

    // 한개의 게시글 삭제
    public void deleteBoard(Long onMeetingIdx, Long onMeetingBoardIdx);

    // 한개의 게시글에 해당하는 댓글 삭제
    public void deleteReply(Long onMeetingIdx, Long onMeetingBoardIdx);

    // 게시글 전체 삭제
    public void deleteAllBoards(Long onMeetingIdx);

    // 게시글 검색
    public List<GetOnMeetingVO> getSearchBoards(Long onMeetingIdx, String keyword);
}
