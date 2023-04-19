package com.metanet.metamungmung.controller.meeting;

import com.metanet.metamungmung.dto.meeting.OnMeetingDTO;
import com.metanet.metamungmung.dto.meeting.OnMeetingMemDTO;
import com.metanet.metamungmung.dto.member.MemberDTO;
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

    @GetMapping("/{id}")
    public OnMeetingDTO getOnMeeting(@PathVariable("id") Long id){
        return service.getOnMeetingById(id);
    }

    @PostMapping("")
    public OnMeetingDTO createOnMeeting(@RequestBody OnMeetingDTO onMeetingDTO){
        OnMeetingMemDTO onMeetingMemDTO = new OnMeetingMemDTO();
        onMeetingMemDTO.setMemberIdx(1L);
        return service.createOnMeeting(onMeetingDTO, onMeetingMemDTO);
    }

//    @PostMapping("/{id}/join")
//    public OnMeetingDTO joinOnMeeting(@RequestBody MemberDTO memberDTO){
//
//    }

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

    @PatchMapping("/{id}")
    public OnMeetingDTO modifyOnMeetingPersonnel(@PathVariable("id") Long id,
                                                 @RequestBody OnMeetingDTO onMeetingDTO){
        onMeetingDTO.setOnMeetingIdx(id);

        return service.modifyOnMeetingPersonnel(onMeetingDTO) == 1 ? service.getOnMeetingById(id) : null;
    }

    @GetMapping("/search")
    public List<OnMeetingDTO> searchOnMeeting(@RequestParam String keyword){
        System.out.println(keyword);
        return service.searchOnMeeting(keyword);
    }

    @DeleteMapping("/{id}/withdraw")
    public int withdrawOnMeeting(@PathVariable("id") Long id){
        OnMeetingMemDTO onMeetingMemDTO = new OnMeetingMemDTO();
        onMeetingMemDTO.setOnMeetingIdx(id);
        onMeetingMemDTO.setMemberIdx(1L);

        onMeetingMemDTO = service.getOnMeetingMemById(onMeetingMemDTO);

        return service.removeOnMeetingMem(onMeetingMemDTO);
    }

    @DeleteMapping("/{id}")
    public int removeOnMeeting(@PathVariable("id") Long id){
        return service.removeOnMeeting(id);
    }

}
