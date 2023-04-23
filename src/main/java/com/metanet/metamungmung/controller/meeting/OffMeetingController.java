package com.metanet.metamungmung.controller.meeting;

import com.metanet.metamungmung.dto.meeting.*;
import com.metanet.metamungmung.dto.member.MemberDTO;
import com.metanet.metamungmung.service.meeting.OnMeetingService;
import com.metanet.metamungmung.vo.meeting.*;
import com.metanet.metamungmung.service.meeting.OffMeetingService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/offMeetings")
public class OffMeetingController {

    @Autowired
    private final OffMeetingService offMeetingService;

    @Autowired
    private final OnMeetingService onMeetingService;

    public OffMeetingController(OffMeetingService offMeetingService, OnMeetingService onMeetingService) {
        this.offMeetingService = offMeetingService;
        this.onMeetingService = onMeetingService;
    }

    /**
     * OFF 모임 조회 API
     * [GET] /offMeetings
     * @return List<OffMeetingDTO>
     **/
    @GetMapping("")
    public List<OffMeetingDTO> showOffMeetingList(@RequestParam("onMeetingIdx") Long onMeetingIdx) {
        List<OffMeetingDTO> offMeetingList = offMeetingService.getOffMeetingList(onMeetingIdx);

        return offMeetingList;
    }

    /**
     * 나의 OFF 모임 조회 API
     * [GET] /offMeetings/myOffMeetings
     * @return List<OffMeetingDTO>
     **/
    @GetMapping("/myOffMeetings")
    public List<OffMeetingDTO> showOffMeetingList(@RequestParam(name = "onMeetingIdx") Long onMeetingIdx, @RequestParam("memberIdx") Long memberIdx) {

        /* 온미팅회원필요 => */
        OnMeetingMemDTO onMeetingMemDTO = new OnMeetingMemDTO();
        onMeetingMemDTO.setOnMeetingIdx(onMeetingIdx);
        onMeetingMemDTO.setMemberIdx(memberIdx);

        System.out.println("onMeetingMemDTO(전) ======> " + onMeetingMemDTO);

        onMeetingMemDTO = onMeetingService.getOnMeetingMemById(onMeetingMemDTO);

        System.out.println("onMeetingMemDTO(후) ======> " + onMeetingMemDTO);

        List<OffMeetingDTO> offMeetingList = offMeetingService.getMyOffMeetingList(
                onMeetingMemDTO.getOnMeetingIdx(),
                onMeetingMemDTO.getMemberIdx(),
                onMeetingMemDTO.getOnMeetingMemIdx()
        );

        return offMeetingList;
    }

    /**
     * OFF 모임 등록 API
     * [POST] /offMeetings
     * @return OffMeetingDTO
     **/
    @PostMapping("")
    public String createOffMeeting(@RequestBody GetOnMeetingDetailVO getOnMeetingDetailVO) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        /* 오프미팅 등록 폼 => onMeet*/
        OffMeetingDTO offMeetingDTO = mapper.map(getOnMeetingDetailVO, OffMeetingDTO.class);

        System.out.println("offMeetingDTO =======>" + offMeetingDTO);

        System.out.println("getOn :::::::: " + getOnMeetingDetailVO);
        System.out.println("getMemberOnIdx :::::::: " + getOnMeetingDetailVO.getMemberIdx());
        System.out.println("getOnMeetingIdx :::::::: " + getOnMeetingDetailVO.getOnMeetingIdx());

        /* 오프모임을 생성한다. */
        offMeetingService.registerOffMeeting(offMeetingDTO);

        /* 온미팅회원필요 => */
        OnMeetingMemDTO onMeetingMemDTO = new OnMeetingMemDTO();
        onMeetingMemDTO.setOnMeetingIdx(getOnMeetingDetailVO.getOnMeetingIdx());
        onMeetingMemDTO.setMemberIdx(getOnMeetingDetailVO.getMemberIdx());

        System.out.println("onMeetingMemDTO(전) ======> " + onMeetingMemDTO);

        onMeetingMemDTO = onMeetingService.getOnMeetingMemById(onMeetingMemDTO);

        System.out.println("onMeetingMemDTO(후) ======> " + onMeetingMemDTO);

        /* 생성된 오프모임의 offMeetingIdx를 가져온다. */
        Long newOffMeetingIdx = offMeetingDTO.getOffMeetingIdx();

        System.out.println("newOffMeetingIdx =====> " + newOffMeetingIdx);

        /* OffMeetingMemDTO 객체 생성 */
        OffMeetingMemDTO offMeetingMemDTO = new OffMeetingMemDTO();

        /* 생성된 오프모임의 offMeetingIdx를 넣어서 오프 모임 회원을 호스트로 생성한다. */
        offMeetingMemDTO = mapper.map(onMeetingMemDTO, OffMeetingMemDTO.class);

        offMeetingMemDTO.setOffMeetingIdx(newOffMeetingIdx);

        System.out.println("offMeetingMemDTO ====> " + offMeetingMemDTO);

        int idx = offMeetingService.registerOffMeetingHost(offMeetingMemDTO);

        /* 결과 */
        String result = "";

        if(idx == 1) {
            result = "오프 모임이 생성되었습니다. 호스트로 지정되었습니다.";
        } else {
            result = "오프 모임 생성에 실패하였습니다.";
        }

        return result;
    }

    /**
     * OFF 모임 상세 조회
     * [GET] /offMeetings/:offMeetingIdx
     * @return OffMeetingDTO
     **/
    @GetMapping("/{offMeetingIdx}")
    public GetOffMeeting2VO showOffMeetingDetail(@PathVariable("offMeetingIdx") Long offMeetingIdx) {
        GetOffMeeting2VO offMeeting = offMeetingService.getOffMeeting(offMeetingIdx);
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
     * [PATCH] /offMeetings/:offMeetingIdx
     * @return OffMeetingDTO
     **/
    @PatchMapping("/{offMeetingIdx}")
    public GetOffMeeting2VO modifyOffMeeting(
            @PathVariable("offMeetingIdx") Long offMeetingIdx, @RequestBody PatchOffMeetingDTO patchOffMeetingDTO) {

        GetOffMeeting2VO getOffMeeting = new GetOffMeeting2VO();
        patchOffMeetingDTO.setOffMeetingIdx(offMeetingIdx);
        int idx = offMeetingService.updateOffMeeting(patchOffMeetingDTO);

        if (idx == 1) {

            getOffMeeting = offMeetingService.getOffMeeting(offMeetingIdx) ;
        }

        return getOffMeeting;
    }

    /**
     * OFF 모임 삭제
     * [POST] /offMeetings/:offMeetingIdx
     * @return String
     **/
    @PostMapping("/{offMeetingIdx}")
    public String deleteOffMeeting(@PathVariable("offMeetingIdx") Long offMeetingIdx) {
        int idx = offMeetingService.deleteOffMeeting(offMeetingIdx);
        String result = "";

        if (idx == 1) {
            result = "삭제되었습니다.";
        } else {
            result = "존재하지 않는 모임 게시글입니다.";
        }

        return result;
    }

    /**
     * OFF 모임 참여하기
     * [POST] /offMeetings/:offMeetingIdx/join
     * @return String
     **/
    @PostMapping("/{offMeetingIdx}/join")
    public String joinOffMeeting(@PathVariable("offMeetingIdx") Long offMeetingIdx, @RequestBody GetMeetingVO getMeetingVO) {
        /* 회원 memberIdx 추출 */
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Long memberIdx = 0L;

        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            MemberDTO memberDTO = (MemberDTO) userDetails;
            memberIdx = memberDTO.getMemberIdx();
        }

        Map<String, Long> map = new HashMap<>();
        map.put("offMeetingIdx", offMeetingIdx);
        map.put("findMemberIdx", memberIdx);

        String result = "";

        /* 이미 참여한 멤버인지 조회*/
        OffMeetingMemDTO findOffMeetingMem = offMeetingService.checkMemberByMemberIdx(map);

        if(findOffMeetingMem != null) {
            result = "이미 참여한 회원입니다.";
        } else if(findOffMeetingMem == null) {
            /* 해당하는 온미팅 정보 찾기 */
            /* 온미팅회원필요 => */
            OnMeetingMemDTO onMeetingMemDTO = new OnMeetingMemDTO();
            onMeetingMemDTO.setOnMeetingIdx(getMeetingVO.getOnMeetingIdx());
            onMeetingMemDTO.setMemberIdx(memberIdx);

            System.out.println("onMeetingMemDTO(전) ======> " + onMeetingMemDTO);

            onMeetingMemDTO = onMeetingService.getOnMeetingMemById(onMeetingMemDTO);

            System.out.println("onMeetingMemDTO(후) ======> " + onMeetingMemDTO);

            /* offMeetingMemDTO에 해당하는 값 저장*/
            OffMeetingMemDTO offMeetingMemDTO = new OffMeetingMemDTO();

            offMeetingMemDTO.setOffMeetingIdx(offMeetingIdx);
            offMeetingMemDTO.setMemberIdx(memberIdx);
            offMeetingMemDTO.setOnMeetingMemIdx(onMeetingMemDTO.getOnMeetingMemIdx());
            offMeetingMemDTO.setOnMeetingIdx(onMeetingMemDTO.getOnMeetingIdx());

            /* offMeetingMemDTO 참여하는 코드 (오프 모임 회원 등록)*/
            int idx1 = offMeetingService.joinOffMeeting(offMeetingMemDTO);

            /* 참여가 완료되면 headcount 증가 */
            int idx2 = offMeetingService.plusHeadcount(offMeetingIdx);

            if(idx1 == 1 && idx2 == 1) {
                result = "참여가 완료되었습니다.";
            }
        }
        return result;
    }

    /**
     * OFF 모임 참여 취소
     * [DELETE] /offMeetings/:offMeetingIdx/join
     * @return String
     **/
    @DeleteMapping("/{offMeetingIdx}/join")
    public String cancelJoinOffMeeting(@PathVariable("offMeetingIdx") Long offMeetingIdx) {
        /* 임시 회원 */
        Long findMemberIdx = 10L;

        Map<String, Long> map = new HashMap<>();
        map.put("offMeetingIdx", offMeetingIdx);
        map.put("findMemberIdx", findMemberIdx);

        String result = "";

        /* 이미 참여한 멤버인지 조회*/
        OffMeetingMemDTO findOffMeetingMem = offMeetingService.checkMemberByMemberIdx(map);

        if(findOffMeetingMem != null) {
            int idx1 = offMeetingService.cancelJoinOffMeeting(map);

            /* 참여가 완료되면 headcount 증가 */
            int idx2 = offMeetingService.minusHeadcount(offMeetingIdx);

            if(idx1 == 1 && idx2 == 1) {
                result = "참여가 취소되었습니다.";
            }
        }

        return result;
    }
}