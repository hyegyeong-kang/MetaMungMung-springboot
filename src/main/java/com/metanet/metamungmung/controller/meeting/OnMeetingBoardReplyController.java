package com.metanet.metamungmung.controller.meeting;


import com.metanet.metamungmung.dto.meeting.OnMeetingBoardDTO;
import com.metanet.metamungmung.dto.meeting.OnMeetingBoardReplyDTO;
import com.metanet.metamungmung.dto.meeting.OnMeetingMemDTO;
import com.metanet.metamungmung.dto.member.MemberDTO;
import com.metanet.metamungmung.service.meeting.OnMeetingBoardReplyService;
import com.metanet.metamungmung.service.meeting.OnMeetingService;
import com.metanet.metamungmung.vo.meeting.GetOnMeetingBoardVO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/onMeetings")
@AllArgsConstructor
public class OnMeetingBoardReplyController {

    @Autowired
    private OnMeetingBoardReplyService service;

    @Autowired
    private OnMeetingService onMeetingService;


    // 해당 게시물 댓글 조회
    ////onMeetings/{onMeetingIdx}/board/{onMeetingBoardIdx}/reply
    @GetMapping("{onMeetingIdx}/board/{onMeetingBoardIdx}/reply")
    public List<GetOnMeetingBoardVO> getReplyList(@PathVariable("onMeetingIdx")Long onMeetingIdx, @PathVariable("onMeetingBoardIdx")Long onMeetingBoardIdx){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberIdx = 0L;

        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            MemberDTO memberDTO = (MemberDTO) userDetails;
            memberIdx = memberDTO.getMemberIdx();
        }

        //Long memberIdx = 1L;
        return service.replyList(onMeetingIdx, onMeetingBoardIdx, memberIdx);
    }

    @GetMapping("{onMeetingIdx}/board/reply")
    public List<OnMeetingBoardReplyDTO> getAllReplyList(@PathVariable("onMeetingIdx")Long onMeetingIdx){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberIdx = 0L;

        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            MemberDTO memberDTO = (MemberDTO) userDetails;
            memberIdx = memberDTO.getMemberIdx();
        }

        //Long memberIdx = 1L;
        return service.getAllReplyList(onMeetingIdx, memberIdx);
    }


    // 해당 게시물 댓글 추가
    @PostMapping("{onMeetingIdx}/board/reply")
    @ResponseBody
    public void addReply(@PathVariable("onMeetingIdx")Long onMeetingIdx, @RequestBody OnMeetingBoardReplyDTO replyDTO) {
        OnMeetingMemDTO onMeetingMemDTO = new OnMeetingMemDTO();
        onMeetingMemDTO.setOnMeetingIdx(onMeetingIdx);
        onMeetingMemDTO.setMemberIdx(replyDTO.getMemberIdx());

//        System.out.println("boarㅇㅇdINSERT::" + replyDTO.getMemberIdx());
//
//        OnMeetingMemDTO omDTO = onMeetingService.getOnMeetingMemById(onMeetingMemDTO);
//        Long onMeetingMemIdx = omDTO.getOnMeetingMemIdx();

        replyDTO.setOnMeetingMemIdx(1L);
        replyDTO.setOnMeetingIdx(onMeetingIdx);

        System.out.println("boardINSERT::" + replyDTO.toString());
        service.addReply(replyDTO);
    }


    // 해당 게시물 댓글 수정
    // /onMeetings/{onMeetingIdx}/board/{onMeetingBoardIdx}/reply/{onMeetingReplyIdx}
    @PatchMapping("{onMeetingIdx}/board/reply/{onMeetingReplyIdx}")
    public void updateReply(@PathVariable("onMeetingIdx")Long onMeetingIdx, @RequestBody OnMeetingBoardReplyDTO replyDTO){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberIdx = 0L;

        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            MemberDTO memberDTO = (MemberDTO) userDetails;
            memberIdx = memberDTO.getMemberIdx();
        }


        Long onMeetingBoardIdx = replyDTO.getOnMeetingBoardIdx();
       // Long memberIdx = 1L;
        service.updateReply(onMeetingIdx, onMeetingBoardIdx, memberIdx);
    }

    // 해당 게시물 댓글 삭제
    @DeleteMapping("{onMeetingIdx}/board/reply/{onMeetingReplyIdx}")
    public void deleteReply(@PathVariable("onMeetingIdx")Long onMeetingIdx, @PathVariable("onMeetingReplyIdx")Long onMeetingReplyIdx){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberIdx = 0L;

        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            MemberDTO memberDTO = (MemberDTO) userDetails;
            memberIdx = memberDTO.getMemberIdx();
        }


      //  Long memberIdx = 1L;
        service.deleteReply(onMeetingIdx, onMeetingReplyIdx, memberIdx);
    }
}

