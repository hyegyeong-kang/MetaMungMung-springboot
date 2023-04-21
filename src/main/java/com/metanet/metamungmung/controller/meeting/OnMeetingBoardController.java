package com.metanet.metamungmung.controller.meeting;

import com.metanet.metamungmung.dto.meeting.OnMeetingBoardDTO;
import com.metanet.metamungmung.dto.meeting.OnMeetingDTO;
import com.metanet.metamungmung.dto.meeting.OnMeetingMemDTO;
import com.metanet.metamungmung.service.meeting.OnMeetingBoardService;
import com.metanet.metamungmung.vo.meeting.GetOnMeetingBoardVO;
import com.metanet.metamungmung.vo.meeting.GetOnMeetingVO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/onMeetings")
@AllArgsConstructor
public class OnMeetingBoardController {

    @Autowired
    private OnMeetingBoardService service;

    // 게시글 목록 조회
    @GetMapping("{onMeetingIdx}/board")
    public List<GetOnMeetingBoardVO> getBoardList(@PathVariable("onMeetingIdx")Long onMeetingIdx)throws Exception {
        return service.getBoardList(onMeetingIdx);
    }

    // 모임 가입한 사용자 리스트 출력
    @GetMapping("{onMeetingIdx}/board/members")
    public List<OnMeetingMemDTO> getOnMeetingMembers(@PathVariable("onMeetingIdx")Long onMeetingIdx) throws Exception {
        return service.getOnMeetingMembers(onMeetingIdx);
    }

    // 게시글 작성
    @PostMapping("{onMeetingIdx}/board")
    public String registerBoard(@PathVariable("onMeetingIdx")Long onMeetingIdx, @RequestBody OnMeetingBoardDTO onMeetingBoardDTO) {
        Long memberIdx = 1L;
        onMeetingBoardDTO.setMemberIdx(memberIdx);
        service.registerBoard(onMeetingIdx, onMeetingBoardDTO);
        return "register ok";
    }

    // 게시글 수정
    @PatchMapping("{onMeetingIdx}/board/{onMeetingBoardIdx}")
    public String updateBoard(@PathVariable("onMeetingIdx")Long onMeetingIdx, @RequestBody OnMeetingBoardDTO boardDTO){
        service.updateBoard(onMeetingIdx, boardDTO);
        return "update ok";
    }

    // 게시글 삭제
    @DeleteMapping("{onMeetingIdx}/board/{onMeetingBoardIdx}")
    public String deleteBoard(@PathVariable("onMeetingIdx")Long onMeetingIdx, @PathVariable("onMeetingBoardIdx")Long onMeetingBoardIdx){
        service.delete(onMeetingIdx, onMeetingBoardIdx);
        return "delete ok";
    }

    // 게시금 검색

    

}
