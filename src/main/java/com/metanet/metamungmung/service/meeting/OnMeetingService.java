package com.metanet.metamungmung.service.meeting;

import com.metanet.metamungmung.dto.meeting.OnMeetingDTO;

public interface OnMeetingService {
    public void createOnMeeting(OnMeetingDTO onMeeting);

    public OnMeetingDTO getOnMeetingById(Long id);
}
