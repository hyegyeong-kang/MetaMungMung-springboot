package com.metanet.metamungmung.controller.meeting;

import com.metanet.metamungmung.dto.meeting.OffMeetingDTO;
import com.metanet.metamungmung.service.meeting.OffMeetingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/offMeetings")
@AllArgsConstructor
public class OffMeetingController {
    private OffMeetingService offMeetingService;

    /**
     * 오프모임 목록 조회 API
     * [GET] /offMeetings
     * @return List<OffMeetingDTO>
     **/
    @GetMapping("")
    public List<OffMeetingDTO> showOffMeetingList() {
        List<OffMeetingDTO> offMeetingList = offMeetingService.getOffMeetingList();

        return offMeetingList;
    }
}
