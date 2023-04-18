package com.metanet.metamungmung.controller.meeting;

import com.metanet.metamungmung.dto.meeting.OnMeetingDTO;
import com.metanet.metamungmung.service.meeting.OnMeetingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/onMeetings")
public class OnMeetingController {

    private OnMeetingService service;

    @GetMapping("")
    public Map<String, List<OnMeetingDTO>> getOnMeetingList(){
        Map<String, List<OnMeetingDTO>> map = new HashMap<>();

        Long memberId = 1L;
        if(service.getOnMeetingListByMember(memberId) != null){
            map.put("myList", service.getOnMeetingListByMember(memberId));
        }
        map.put("recommendList", service.getRecommendOnMeetingList(memberId));

        return map;
    }

    @PostMapping("")
    public OnMeetingDTO createOnMeeting(@RequestBody OnMeetingDTO onMeetingDTO){
        service.createOnMeeting(onMeetingDTO);

        OnMeetingDTO newOnMeeting = service.getOnMeetingById(onMeetingDTO.getOnMeetingIdx());

        return newOnMeeting;
    }

    @PutMapping("/{id}")
    public OnMeetingDTO modifyOnMeeting(@PathVariable("id") Long id,
                                                        @RequestBody OnMeetingDTO onMeetingDTO){
        onMeetingDTO.setOnMeetingIdx(id);
        System.out.println(onMeetingDTO);

//        HttpStatus status = HttpStatus.BAD_REQUEST;
//        if(service.modifyOnMeeting(onMeetingDTO) == 1) {
//            status = HttpStatus.OK;
//        }
//
//        return ResponseEntity.status(status).body(onMeetingDTO);
        return service.modifyOnMeeting(onMeetingDTO) == 1 ? onMeetingDTO : null;
    }

    @DeleteMapping("/{id}")
    public int removeOnMeeting(@PathVariable("id") Long id){
        return service.removeOnMeeting(id);
    }

}
