package com.metanet.metamungmung.controller.meeting;

import com.metanet.metamungmung.dto.meeting.OffMeetingDTO;
import com.metanet.metamungmung.dto.meeting.PatchOffMeetingDTO;
import com.metanet.metamungmung.vo.meeting.GetOffMeetingVO;
import com.metanet.metamungmung.service.meeting.OffMeetingService;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offMeetings")
@AllArgsConstructor
public class OffMeetingController {
    private OffMeetingService offMeetingService;

    /**
     * OFF 모임 등록 API
     * [GET] /offMeetings
     * @return List<OffMeetingDTO>
     **/
    @GetMapping("")
    public List<OffMeetingDTO> showOffMeetingList() {
        List<OffMeetingDTO> offMeetingList = offMeetingService.getOffMeetingList();

        return offMeetingList;
    }

    /**
     * OFF 모임 등록 API
     * [POST] /offMeetings
     * @return OffMeetingDTO
     **/
    @PostMapping("")
    public void createOffMeeting(@RequestBody OffMeetingDTO offMeetingDTO) {
        offMeetingService.registerOffMeeting(offMeetingDTO);
    }

    /**
     * OFF 모임 상세 조회
     * [GET] /offMeetings/:offMeetingIdx
     * @return OffMeetingDTO
     **/
    @GetMapping("/{offMeetingIdx}")
    public OffMeetingDTO showOffMeetingDetail(@PathVariable("offMeetingIdx") Long offMeetingIdx) {
        OffMeetingDTO offMeeting = offMeetingService.getOffMeeting(offMeetingIdx);
        return offMeeting;
    }

    /**
     * OFF 모임 참여자 조회
     * [GET] /offMeetings/:offMeetingIdx/offMeetingMembers
     * @return List<GetOffMeetingVO>
     **/
    @GetMapping("/{offMeetingIdx}/offMeetingMembers")
    public List<GetOffMeetingVO> showOffMeetingMembers(@PathVariable("offMeetingIdx") Long offMeetingIdx) {
        List<GetOffMeetingVO> offMeetingMembers = offMeetingService.getOffMeetingMembers(offMeetingIdx);
        return offMeetingMembers;
    }

    /**
     * OFF 모임 수정
     * [PATCh] /offMeetings/:offMeetingIdx
     * @return OffMeetingDTO
     **/
    @PatchMapping("/{offMeetingIdx}")
    public OffMeetingDTO modifyOffMeeting(@PathVariable("offMeetingIdx") Long offMeetingIdx, @RequestBody PatchOffMeetingDTO patchOffMeetingDTO) {
        OffMeetingDTO newOffMeeting = null;
        patchOffMeetingDTO.setOffMeetingIdx(offMeetingIdx);
        int idx =offMeetingService.updateOffMeeting(patchOffMeetingDTO);

        if (idx == 1) {
            newOffMeeting = offMeetingService.getOffMeeting(offMeetingIdx) ;
        }

        return newOffMeeting;
    }
}
