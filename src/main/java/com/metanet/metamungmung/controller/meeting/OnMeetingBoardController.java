package com.metanet.metamungmung.controller.meeting;

import com.metanet.metamungmung.dto.meeting.OnMeetingBoardDTO;
import com.metanet.metamungmung.dto.meeting.OnMeetingDTO;
import com.metanet.metamungmung.dto.meeting.OnMeetingMemDTO;
import com.metanet.metamungmung.service.meeting.OnMeetingBoardService;
import com.metanet.metamungmung.vo.meeting.GetOnMeetingBoardVO;
import com.metanet.metamungmung.vo.meeting.GetOnMeetingVO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/onMeetings")
@AllArgsConstructor
public class OnMeetingBoardController {

    @Autowired
    private OnMeetingBoardService service;
//    @Autowired
//    private OnMeetingBoardService onMeetingService;

    // 게시글 목록 조회
    @GetMapping("{onMeetingIdx}/board")
    public List<GetOnMeetingVO> getBoardList(@PathVariable("onMeetingIdx")Long onMeetingIdx)throws Exception {
        System.out.println("$$$$KANG" + onMeetingIdx);

        return service.getBoardList(onMeetingIdx);
    }

    // 모임 가입한 사용자 리스트 조회
    @GetMapping("{onMeetingIdx}/board/members")
    public List<OnMeetingMemDTO> getOnMeetingMembers(@PathVariable("onMeetingIdx")Long onMeetingIdx) throws Exception {
        return service.getOnMeetingMembers(onMeetingIdx);
    }

//    @GetMapping("{onMeetingIdx}/board/members")
//    public String getOnMeetingMembersCnt(@PathVariable("onMeetingIdx") Long onMeetingIdx) throws Exception {
//        return service.getCnt(onMeetingIdx);
//    }



    // 게시글 작성
    @PostMapping("{onMeetingIdx}/board")
    public void registerBoard(@PathVariable("onMeetingIdx")Long onMeetingIdx, @RequestBody OnMeetingBoardDTO onMeetingBoardDTO) {
        System.out.println("등록컨트롤러!!");


//        ModelMapper mapper = new ModelMapper();
//        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//
//        OnMeetingBoardDTO onMeetingBoardDTO = mapper.map(getOnMeetingBoardDetailVO, OnMeetingBoardDTO.class);

        // onMeetingMemIdx 회원 가져오기
//        OnMeetingMemDTO onMeetingMemDTO = new OnMeetingMemDTO();
//        onMeetingMemDTO.setOnMeetingIdx(getOnMeetingBoardDetailVO.getOnMeetingIdx());
//        onMeetingMemDTO.setMemberIdx(getOnMeetingBoardDetailVO.getMemberIdx());

       // onMeetingMemDTO = onMeetingService.getOnMeetingMemById(onMeetingMemDTO);
        onMeetingBoardDTO.setOnMeetingMemIdx(1L);
        onMeetingBoardDTO.setOnMeetingIdx(1L);


        System.out.println("등록컨트롤러!!" + onMeetingBoardDTO.toString());
        //onMeetingBoardDTO.setMemberIdx(memberIdx);
        service.registerBoard(onMeetingBoardDTO);
        //return "register ok";
    }

    // 게시글 수정
    @PatchMapping("{onMeetingIdx}/board")
    public String updateBoard(@PathVariable("onMeetingIdx")Long onMeetingIdx, @RequestBody OnMeetingBoardDTO boardDTO){
        boardDTO.setOnMeetingIdx(onMeetingIdx);
        service.updateBoard(boardDTO);
        return "update ok";
    }

    // 게시글 삭제
    @DeleteMapping("{onMeetingIdx}/board")
    public String deleteBoard(@PathVariable("onMeetingIdx")Long onMeetingIdx, @RequestParam Long onMeetingBoardIdx){
        service.delete(onMeetingIdx, onMeetingBoardIdx);
        return "delete ok";
    }

    // 게시글 검색

    

}
