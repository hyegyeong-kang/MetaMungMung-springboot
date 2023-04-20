package com.metanet.metamungmung.controller.meeting;

import com.metanet.metamungmung.dto.meeting.*;
import com.metanet.metamungmung.service.meeting.OnMeetingService;
import com.metanet.metamungmung.vo.meeting.GetOffMeetingVO;
import com.metanet.metamungmung.service.meeting.OffMeetingService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String createOffMeeting(@RequestBody OffMeetingDTO offMeetingDTO) {
        /* 오프모임을 생성한다. */
        offMeetingService.registerOffMeeting(offMeetingDTO);

        /* 생성된 오프모임의 offMeetingIdx를 가져온다. */
        Long newOffMeetingIdx = offMeetingDTO.getOffMeetingIdx();

        /* OffMeetingMemDTO 객체 생성 */
        OffMeetingMemDTO offMeetingMemDTO = new OffMeetingMemDTO();

        /* 생성된 오프모임의 offMeetingIdx를 넣어서 오프 모임 회원을 호스트로 생성한다. */
        offMeetingMemDTO.setOffMeetingIdx(newOffMeetingIdx);

        /* 임시 onMeetingMemIdx, onMeetingIdx */
        offMeetingMemDTO.setOnMeetingMemIdx(45L);
        offMeetingMemDTO.setOnMeetingIdx(14L);
        offMeetingMemDTO.setMemberIdx(11L);

        /* 등록과 동시에 호스트가 된다. */
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
     * [PATCH] /offMeetings/:offMeetingIdx
     * @return OffMeetingDTO
     **/
    @PatchMapping("/{offMeetingIdx}")
    public OffMeetingDTO modifyOffMeeting(
            @PathVariable("offMeetingIdx") Long offMeetingIdx, @RequestBody PatchOffMeetingDTO patchOffMeetingDTO) {

        OffMeetingDTO newOffMeeting = new OffMeetingDTO();
        patchOffMeetingDTO.setOffMeetingIdx(offMeetingIdx);
        int idx = offMeetingService.updateOffMeeting(patchOffMeetingDTO);

        if (idx == 1) {
            newOffMeeting = offMeetingService.getOffMeeting(offMeetingIdx) ;
        }

        return newOffMeeting;
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
    public String joinOffMeeting(@PathVariable("offMeetingIdx") Long offMeetingIdx, @RequestBody OffMeetingMemDTO offMeetingMemDTO) {
        /* 임시 회원 */
        Long findMemberIdx = 10L;

        offMeetingMemDTO.setOffMeetingIdx(offMeetingIdx);

        Map<String, Long> map = new HashMap<>();
        map.put("offMeetingIdx", offMeetingIdx);
        map.put("findMemberIdx", findMemberIdx);

        String result = "";

        /* 이미 참여한 멤버인지 조회*/
        OffMeetingMemDTO findOffMeetingMem = offMeetingService.checkMemberByMemberIdx(map);

        if(findOffMeetingMem != null) {
            result = "이미 참여한 회원입니다.";
        } else if(findOffMeetingMem == null) {
            /* offMeetingMemDTO에 해당하는 값 저장*/
            offMeetingMemDTO.setMemberIdx(findMemberIdx);
            offMeetingMemDTO.setOnMeetingMemIdx(44L);
            offMeetingMemDTO.setOnMeetingIdx(14L);

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
