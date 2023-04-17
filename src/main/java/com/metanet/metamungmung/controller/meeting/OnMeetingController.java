package com.metanet.metamungmung.controller.meeting;

import com.metanet.metamungmung.dto.meeting.OnMeetingDTO;
import com.metanet.metamungmung.service.meeting.OnMeetingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/onMeetings")
public class OnMeetingController {

    private OnMeetingService service;

    @PostMapping("")
    public OnMeetingDTO createOnMeeting(@RequestBody OnMeetingDTO onMeetingDTO){
        System.out.println(onMeetingDTO);

        service.createOnMeeting(onMeetingDTO);

        OnMeetingDTO newOnMeeting = service.getOnMeetingById(onMeetingDTO.getOnMeetingIdx());

        return newOnMeeting;
    }
}
