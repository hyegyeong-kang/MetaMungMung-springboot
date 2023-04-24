package com.metanet.metamungmung.controller.meeting;

import com.metanet.metamungmung.dto.meeting.OnMeetingDTO;
import com.metanet.metamungmung.dto.meeting.OnMeetingMemDTO;
import com.metanet.metamungmung.dto.member.MemberDTO;
import com.metanet.metamungmung.service.meeting.OnMeetingService;
import com.metanet.metamungmung.service.member.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberIdx = 0L;

        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            MemberDTO memberDTO = (MemberDTO) userDetails;
            memberIdx = memberDTO.getMemberIdx();
        }

        Map<String, List<OnMeetingDTO>> map = new HashMap<>();

//        Long memberIdx = 1L;
//        System.out.println("member  "+ memberDTO);

//        System.out.println("idx " + memberIdx);

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
    public OnMeetingDTO createOnMeeting(@RequestBody OnMeetingDTO onMeetingDTO){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberIdx = 0L;

        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            MemberDTO memberDTO = (MemberDTO) userDetails;
            memberIdx = memberDTO.getMemberIdx();

            System.out.println("memberIdx 나와주세요~~~~~~~~~~~~~~~~~"+ memberIdx);
        }

        OnMeetingMemDTO onMeetingMemDTO = new OnMeetingMemDTO();
        onMeetingMemDTO.setMemberIdx(memberIdx);
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
    public List<OnMeetingDTO> searchOnMeeting(@RequestParam(name="keywords") String keywords,
                                              @RequestParam(name="category") String category,
                                              @RequestParam(name="address") String address){
        return service.searchOnMeeting(keywords, category, address);
    }

    @PostMapping("/{id}/join")
    public OnMeetingDTO joinOnMeeting(@PathVariable("id") Long id){
        return service.joinOnMeeting(id);
    }

    @DeleteMapping("/{id}/withdraw")
    public int withdrawOnMeeting(@PathVariable("id") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberIdx = 0L;

        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            MemberDTO memberDTO = (MemberDTO) userDetails;
            memberIdx = memberDTO.getMemberIdx();

            System.out.println("memberIdx 나와주세요~~~~~~~~~~~~~~~~~"+ memberIdx);
        }

        return service.removeOnMeetingMem(id, memberIdx);
    }

    @DeleteMapping("/{id}/exile")
    public int withdrawOnMeeting(@PathVariable("id") Long id, @RequestParam(name = "memberIdx") Long memberIdx){

        return service.removeOnMeetingMem(id, memberIdx);
    }

    @DeleteMapping("/{id}")
    public int removeOnMeeting(@PathVariable("id") Long id){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberIdx = 0L;

        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            MemberDTO memberDTO = (MemberDTO) userDetails;
            memberIdx = memberDTO.getMemberIdx();

            System.out.println("memberIdx 나와주세요~~~~~~~~~~~~~~~~~" + memberIdx);
        }

        return service.removeOnMeeting(id, memberIdx);
    }

}
