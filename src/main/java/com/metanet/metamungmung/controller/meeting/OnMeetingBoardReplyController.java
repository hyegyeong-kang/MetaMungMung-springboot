package com.metanet.metamungmung.controller.meeting;


import com.metanet.metamungmung.dto.meeting.OnMeetingBoardDTO;
import com.metanet.metamungmung.dto.meeting.OnMeetingBoardReplyDTO;
import com.metanet.metamungmung.service.meeting.OnMeetingBoardReplyService;
import com.metanet.metamungmung.vo.meeting.GetOnMeetingBoardVO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/onMeetings")
@AllArgsConstructor
public class OnMeetingBoardReplyController {

    @Autowired
    private OnMeetingBoardReplyService service;
    // 해당 게시물 댓글 조회
    ////onMeetings/{onMeetingIdx}/board/{onMeetingBoardIdx}/reply
    @GetMapping("{onMeetingIdx}/board/{onMeetingBoardIdx}/reply")
    public List<GetOnMeetingBoardVO> getReplyList(@PathVariable("onMeetingIdx")Long onMeetingIdx, @PathVariable("onMeetingBoardIdx")Long onMeetingBoardIdx){
        Long memberIdx = 1L;
        return service.replyList(onMeetingIdx, onMeetingBoardIdx, memberIdx);
    }

    @GetMapping("{onMeetingIdx}/board/reply")
    public List<GetOnMeetingBoardVO> getAllReplyList(@PathVariable("onMeetingIdx")Long onMeetingIdx){
        Long memberIdx = 1L;
        return service.getAllReplyList(onMeetingIdx, memberIdx);
    }


    // 해당 게시물 댓글 추가
    @PostMapping("{onMeetingIdx}/board/reply")
    @ResponseBody
    public void addReply(@PathVariable("onMeetingIdx")Long onMeetingIdx, @RequestBody OnMeetingBoardReplyDTO replyDTO) {
        replyDTO.setOnMeetingIdx(onMeetingIdx);
        System.out.println("boardINSERT::" + replyDTO.toString());
        service.addReply(replyDTO);
    }


    // 해당 게시물 댓글 수정
    // /onMeetings/{onMeetingIdx}/board/{onMeetingBoardIdx}/reply/{onMeetingReplyIdx}
    @PatchMapping("{onMeetingIdx}/board/reply/{onMeetingReplyIdx}")
    public void updateReply(@PathVariable("onMeetingIdx")Long onMeetingIdx, @RequestBody OnMeetingBoardReplyDTO replyDTO){
        Long onMeetingBoardIdx = replyDTO.getOnMeetingBoardIdx();
        Long memberIdx = 1L;
        service.updateReply(onMeetingIdx, onMeetingBoardIdx, memberIdx);
    }

    // 해당 게시물 댓글 삭제
    @DeleteMapping("{onMeetingIdx}/board/reply/{onMeetingReplyIdx}")
    public void deleteReply(@PathVariable("onMeetingIdx")Long onMeetingIdx, @PathVariable("onMeetingReplyIdx")Long onMeetingReplyIdx){
        Long memberIdx = 1L;
        service.deleteReply(onMeetingIdx, onMeetingReplyIdx, memberIdx);
    }
}

