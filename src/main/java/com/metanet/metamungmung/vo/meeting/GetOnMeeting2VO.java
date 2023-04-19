package com.metanet.metamungmung.vo.meeting;

import com.metanet.metamungmung.dto.member.MemberDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class GetOnMeeting2VO {
    private Long memberIdx;
    private List<MemberDTO> memberList;
}
