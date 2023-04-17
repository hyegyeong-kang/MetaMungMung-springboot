package com.metanet.metamungmung.service.meeting;

import com.metanet.metamungmung.dto.meeting.OnMeetingDTO;
import com.metanet.metamungmung.mapper.meeting.OnMeetingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OnMeetingServiceImpl implements OnMeetingService {
    @Autowired
    private OnMeetingMapper mapper;

    @Override
    public void createOnMeeting(OnMeetingDTO onMeeting){
        mapper.createOnMeeting(onMeeting);
    }

    @Override
    public OnMeetingDTO getOnMeetingById(Long id){
        return mapper.getOnMeetingById(id);
    }
}
