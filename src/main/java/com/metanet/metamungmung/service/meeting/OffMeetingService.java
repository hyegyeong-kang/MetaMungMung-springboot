package com.metanet.metamungmung.service.meeting;

import com.metanet.metamungmung.dto.meeting.OffMeetingDTO;
import com.metanet.metamungmung.dto.meeting.OffMeetingMemDTO;
import com.metanet.metamungmung.dto.meeting.PatchOffMeetingDTO;
import com.metanet.metamungmung.vo.meeting.GetOffMeeting2VO;
import com.metanet.metamungmung.vo.meeting.GetOffMeetingVO;

import java.util.List;
import java.util.Map;

public interface OffMeetingService {
    public List<OffMeetingDTO> getOffMeetingList(Long onMeetingIdx);

    public GetOffMeeting2VO getOffMeeting(Long offMeetingIdx);

    public void registerOffMeeting(OffMeetingDTO offMeetingDTO);

    public List<GetOffMeetingVO> getOffMeetingMembers(Long offMeetingIdx);

    public int updateOffMeeting(PatchOffMeetingDTO patchOffMeetingDTO);

    public int deleteOffMeeting(Long offMeetingIdx);

    public int joinOffMeeting(OffMeetingMemDTO offMeetingMemDTO);

    public int registerOffMeetingHost(OffMeetingMemDTO offMeetingMemDTO);

    public OffMeetingMemDTO checkMemberByMemberIdx(Map<String, Long> map);

    public int cancelJoinOffMeeting(Map<String, Long> map);

    public int plusHeadcount(Long offMeetingIdx);

    public int minusHeadcount(Long offMeetingIdx);

    public List<OffMeetingDTO> getMyOffMeetingList(Long onMeetingIdx, Long memberIdx, Long onMeetingMemIdx);
}
