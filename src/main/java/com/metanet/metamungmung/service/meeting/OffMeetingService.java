package com.metanet.metamungmung.service.meeting;

import com.metanet.metamungmung.dto.meeting.OffMeetingDTO;
import com.metanet.metamungmung.dto.meeting.PatchOffMeetingDTO;
import com.metanet.metamungmung.vo.meeting.GetOffMeetingVO;

import java.util.List;

public interface OffMeetingService {
    public List<OffMeetingDTO> getOffMeetingList();

    public OffMeetingDTO getOffMeeting(Long offMeetingIdx);

    public void registerOffMeeting(OffMeetingDTO offMeetingDTO);

    public List<GetOffMeetingVO> getOffMeetingMembers(Long offMeetingIdx);

    public int updateOffMeeting(PatchOffMeetingDTO patchOffMeetingDTO);

}
