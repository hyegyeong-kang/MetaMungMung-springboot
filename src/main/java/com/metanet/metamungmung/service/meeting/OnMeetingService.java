package com.metanet.metamungmung.service.meeting;

import com.metanet.metamungmung.dto.meeting.OnMeetingDTO;

import java.util.List;

public interface OnMeetingService {
    public void createOnMeeting(OnMeetingDTO onMeeting);

    public OnMeetingDTO getOnMeetingById(Long id);

    public int modifyOnMeeting(OnMeetingDTO onMeetingDTO);

    public int removeOnMeeting(Long id);

    public List<OnMeetingDTO> getRecommendOnMeetingList(Long memberIdx);

    public List<OnMeetingDTO> getOnMeetingListByMember(Long memberIdx);
}
