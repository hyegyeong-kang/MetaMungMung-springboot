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
    public GetOnMeetingBoardVO getReplyList(@PathVariable("onMeetingIdx")Long onMeetingIdx, @PathVariable("onMeetingBoardIdx")Long onMeetingBoardIdx){
        Long memberIdx = 1L;
        return service.replyList(onMeetingIdx, onMeetingBoardIdx, memberIdx);
    }


    // 해당 게시물 댓글 추가
    @PostMapping("{onMeetingIdx}/board/reply")
    @ResponseBody
    public void addReply(@PathVariable("onMeetingIdx")Long onMeetingIdx, @RequestBody OnMeetingBoardReplyDTO replyDTO) {
        service.addReply(onMeetingIdx, replyDTO);
    }


    // 해당 게시물 댓글 수정
    // /onMeetings/{onMeetingIdx}/board/{onMeetingBoardIdx}/reply/{onMeetingReplyIdx}
    @PatchMapping("{onMeetingIdx}/board/reply/{onMeetingReplyIdx}")
    public void updateReply(@PathVariable("onMeetingIdx")Long onMeetingIdx, @RequestBody OnMeetingBoardReplyDTO replyDTO){
        service.updateReply(onMeetingIdx, replyDTO);
    }

    // 해당 게시물 댓글 삭제
    @DeleteMapping("{onMeetingIdx}/board/reply/{onMeetingReplyIdx}")
    public void deleteReply(@PathVariable("onMeetingIdx")Long onMeetingIdx, @PathVariable("onMeetingReplyIdx")Long onMeetingReplyIdx){
        service.deleteReply(onMeetingIdx, onMeetingReplyIdx);
    }
}
