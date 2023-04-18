package com.metanet.metamungmung.service.meeting;

import com.metanet.metamungmung.dto.meeting.OnMeetingDTO;
import com.metanet.metamungmung.mapper.meeting.OnMeetingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public int modifyOnMeeting(OnMeetingDTO onMeetingDTO) {
        return mapper.modifyOnMeeting(onMeetingDTO);
    }

    @Override
    public int removeOnMeeting(Long id) {
        return mapper.removeOnMeeting(id);
    }

    @Override
    public List<OnMeetingDTO> getRecommendOnMeetingList(Long memberIdx) {
        return mapper.getRecommendOnMeetingList(memberIdx);
    }

    @Override
    public List<OnMeetingDTO> getOnMeetingListByMember(Long memberIdx) {
        return mapper.getOnMeetingListByMember(memberIdx);
    }
}
