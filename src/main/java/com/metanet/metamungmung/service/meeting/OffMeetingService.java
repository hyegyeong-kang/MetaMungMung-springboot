package com.metanet.metamungmung.service.meeting;

import com.metanet.metamungmung.dto.meeting.OffMeetingDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OffMeetingService {
    public List<OffMeetingDTO> getOffMeetingList();

    public OffMeetingDTO getOffMeeting(Long offMeetingIdx);

    public void registerOffMeeting(OffMeetingDTO offMeetingDTO);

}
