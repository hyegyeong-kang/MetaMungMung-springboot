package com.metanet.metamungmung.controller.meeting;

import com.metanet.metamungmung.dto.meeting.OnMeetingDTO;
import com.metanet.metamungmung.dto.meeting.OnMeetingMemDTO;
import com.metanet.metamungmung.dto.member.MemberDTO;
import com.metanet.metamungmung.security.JwtFilter;
import com.metanet.metamungmung.service.meeting.OnMeetingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/onMeetings")
public class OnMeetingController {

    private OnMeetingService service;
    @Autowired
    private JwtFilter jwtFilter;

    @GetMapping("")
    public Map<String, List<OnMeetingDTO>> getOnMeetingList(HttpServletRequest request){
        Map<String, List<OnMeetingDTO>> map = new HashMap<>();

        String token = request.getHeader("Authorization").substring(7);
        Long memberIdx = jwtFilter.getMemberIdxFromToken(token);
        // memberIdx를 활용한 로직 구현
        System.out.println(memberIdx);

        if(service.getOnMeetingListByMember(memberIdx) != null){
            map.put("myList", service.getOnMeetingListByMember(memberIdx));
        }
        map.put("recommendList", service.getRecommendOnMeetingList(memberIdx));

        return map;
    }

    @GetMapping("/{id}")
    public OnMeetingDTO getOnMeeting(@PathVariable("id") Long id){
        return service.getOnMeetingById(id);
    }

    @PostMapping("")
    public OnMeetingDTO createOnMeeting(@RequestBody OnMeetingDTO onMeetingDTO, HttpServletRequest request){
        String token = request.getHeader("Authorization").substring(7);
        Long memberIdx = jwtFilter.getMemberIdxFromToken(token);

        OnMeetingMemDTO onMeetingMemDTO = new OnMeetingMemDTO();
        onMeetingMemDTO.setMemberIdx(memberIdx);
        return service.createOnMeeting(onMeetingDTO, onMeetingMemDTO);
    }

    @PutMapping("/{id}")
    public OnMeetingDTO modifyOnMeeting(@PathVariable("id") Long id,
                                        @RequestBody OnMeetingDTO onMeetingDTO,
                                        HttpServletRequest request){

        String token = request.getHeader("Authorization").substring(7);
        Long memberIdx = jwtFilter.getMemberIdxFromToken(token);

        OnMeetingDTO originOnMeeting = service.getOnMeetingById(id);

        if(originOnMeeting.getHostIdx() == memberIdx){
            onMeetingDTO.setOnMeetingIdx(id);
            return service.modifyOnMeeting(onMeetingDTO) == 1 ? service.getOnMeetingById(id) : null;
        }

        return null;
    }

    @PatchMapping("/{id}")
    public OnMeetingDTO modifyOnMeetingPersonnel(@PathVariable("id") Long id,
                                                 @RequestBody OnMeetingDTO onMeetingDTO,
                                                 HttpServletRequest request){
        String token = request.getHeader("Authorization").substring(7);
        Long memberIdx = jwtFilter.getMemberIdxFromToken(token);

        OnMeetingDTO originOnMeeting = service.getOnMeetingById(onMeetingDTO.getOnMeetingIdx());

        if(originOnMeeting.getHostIdx() == memberIdx){
            onMeetingDTO.setOnMeetingIdx(id);
            return service.modifyOnMeetingPersonnel(onMeetingDTO) == 1 ? service.getOnMeetingById(id) : null;
        }
        return null;
    }

    @GetMapping("/search")
    public List<OnMeetingDTO> searchOnMeeting(@RequestParam String keyword){

        return service.searchOnMeeting(keyword);
    }

    @PostMapping("/{id}/join")
    public OnMeetingDTO joinOnMeeting(@PathVariable("id") Long id, HttpServletRequest request){
        String token = request.getHeader("Authorization").substring(7);
        Long memberIdx = jwtFilter.getMemberIdxFromToken(token);

        return service.joinOnMeeting(id, memberIdx);
    }

    @DeleteMapping("/{id}/withdraw")
    public int withdrawOnMeeting(@PathVariable("id") Long id, HttpServletRequest request){
        String token = request.getHeader("Authorization").substring(7);
        Long memberIdx = jwtFilter.getMemberIdxFromToken(token);

        return service.removeOnMeetingMem(id, memberIdx);
    }

    @DeleteMapping("/{id}")
    public int removeOnMeeting(@PathVariable("id") Long id, HttpServletRequest request){
        String token = request.getHeader("Authorization").substring(7);
        Long memberIdx = jwtFilter.getMemberIdxFromToken(token);

        return service.removeOnMeeting(id, memberIdx);
    }

}
