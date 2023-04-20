package com.metanet.metamungmung.controller.meeting;

import com.metanet.metamungmung.dto.meeting.OnMeetingBoardDTO;
import com.metanet.metamungmung.dto.meeting.OnMeetingDTO;
import com.metanet.metamungmung.dto.meeting.OnMeetingMemDTO;
import com.metanet.metamungmung.service.meeting.OnMeetingBoardService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/onMeetings")
@AllArgsConstructor
public class OnMeetingBoardController {

    @Autowired
    private OnMeetingBoardService service;

    // 게시글 목록 조회
    @GetMapping("{onMeetingIdx}/board")
    public List<OnMeetingBoardDTO> getBoardList(@PathVariable("onMeetingIdx")Long onMeetingIdx)throws Exception {
        return service.getBoardList(onMeetingIdx);
    }

    // 모임 가입한 사용자 리스트 출력
    @GetMapping("{onMeetingIdx}/board/members")
    public List<OnMeetingMemDTO> getOnMeetingMembers(@PathVariable("onMeetingIdx")Long onMeetingIdx) throws Exception {
        return service.getOnMeetingMembers(onMeetingIdx);
    }


}
