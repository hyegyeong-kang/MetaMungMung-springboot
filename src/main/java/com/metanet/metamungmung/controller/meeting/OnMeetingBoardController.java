package com.metanet.metamungmung.controller.meeting;

import com.metanet.metamungmung.dto.meeting.OnMeetingBoardDTO;
import com.metanet.metamungmung.dto.meeting.OnMeetingDTO;
import com.metanet.metamungmung.dto.meeting.OnMeetingMemDTO;
import com.metanet.metamungmung.service.meeting.OnMeetingBoardService;
import com.metanet.metamungmung.service.meeting.OnMeetingService;
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

    @Autowired
    private OnMeetingService onMeetingService;


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

        OnMeetingMemDTO onMeetingMemDTO = new OnMeetingMemDTO();
        onMeetingMemDTO.setOnMeetingIdx(onMeetingIdx);
        onMeetingMemDTO.setMemberIdx(onMeetingBoardDTO.getMemberIdx());

        //OnMeetingMemDTO omDTO = onMeetingService.getOnMeetingMemById(onMeetingMemDTO);
        //Long onMeetingMemIdx = omDTO.getOnMeetingMemIdx();

        onMeetingBoardDTO.setOnMeetingMemIdx(1L);
        onMeetingBoardDTO.setOnMeetingIdx(onMeetingIdx);

        System.out.println("등록컨트롤러!!" + onMeetingBoardDTO.toString());

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

    // 한개의 게시글 삭제
    @DeleteMapping("{onMeetingIdx}/board")
    public void deleteBoard(@PathVariable("onMeetingIdx") Long onMeetingIdx, @RequestParam(name = "onMeetingBoardIdx") Long onMeetingBoardIdx){
      // System.out.println("삭제되나여??" + getBoardIndex.getOnMeetingBoardIdx());
        service.delete(onMeetingIdx, onMeetingBoardIdx);
       // return "delete ok";
    }

    // 게시글 검색
    @GetMapping("{onMeetingIdx}/board/search")
    public List<GetOnMeetingVO> getSearchBoards(@PathVariable("onMeetingIdx")Long onMeetingIdx,@RequestParam(name = "keyword") String keyword) {
        System.out.println("강강강" +keyword);
        List<GetOnMeetingVO> boardList = service.getSearchBoards(onMeetingIdx,keyword);
        return boardList;
    }

    

}
