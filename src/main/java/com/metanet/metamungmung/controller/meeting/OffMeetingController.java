package com.metanet.metamungmung.controller.meeting;

import com.metanet.metamungmung.dto.meeting.OffMeetingDTO;
import com.metanet.metamungmung.service.meeting.OffMeetingService;
import lombok.AllArgsConstructor;
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
}
