package com.metanet.metamungmung.mapper.meeting;

import com.metanet.metamungmung.dto.meeting.OnMeetingDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OnMeetingMapper {
    public void createOnMeeting(OnMeetingDTO onMeetingDTO);

    public OnMeetingDTO getOnMeetingById(Long id);

    public int modifyOnMeeting(OnMeetingDTO onMeetingDTO);

    public int removeOnMeeting(Long id);

    public List<OnMeetingDTO> getRecommendOnMeetingList(Long memberIdx);

    public List<OnMeetingDTO> getOnMeetingListByMember(Long memberIdx);
}
