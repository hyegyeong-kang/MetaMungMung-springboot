package com.metanet.metamungmung.mapper.meeting;

import com.metanet.metamungmung.dto.meeting.OnMeetingDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OnMeetingMapper {
    public void createOnMeeting(OnMeetingDTO onMeeting);

    public OnMeetingDTO getOnMeetingById(Long id);
}
