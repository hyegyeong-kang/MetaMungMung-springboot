package com.metanet.metamungmung.service.meeting;

import com.metanet.metamungmung.dto.meeting.OnMeetingDTO;
import com.metanet.metamungmung.dto.meeting.OnMeetingMemDTO;
import com.metanet.metamungmung.mapper.meeting.OnMeetingMapper;

import java.util.List;

public interface OnMeetingService {
    public OnMeetingDTO createOnMeeting(OnMeetingDTO onMeetingDTO, OnMeetingMemDTO onMeetingMemDTO);

    public OnMeetingDTO getOnMeetingById(Long onMeetingIdx);

    public int modifyOnMeeting(OnMeetingDTO onMeetingDTO);
    public int modifyOnMeetingPersonnel(OnMeetingDTO onMeetingDTO);

    public int removeOnMeeting(Long id);

    public List<OnMeetingDTO> searchOnMeeting(String searchKeyword);

    public List<OnMeetingDTO> getRecommendOnMeetingList(Long memberIdx);

//    public List<OnMeetingDTO> getRecommendOnMeetingListWithCategory(String memberIdx, String category);

    public List<OnMeetingDTO> getOnMeetingListByMember(Long memberIdx);

    public OnMeetingDTO joinOnMeeting(Long onMeetingIdx);

    public OnMeetingMemDTO getOnMeetingMemById(OnMeetingMemDTO onMeetingMemDTO);

    public int removeOnMeetingMem(Long onMeetingIdx);
}
