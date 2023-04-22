package com.metanet.metamungmung.mapper.meeting;

import com.metanet.metamungmung.dto.meeting.OnMeetingDTO;
import com.metanet.metamungmung.dto.meeting.OnMeetingMemDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OnMeetingMapper {
    public void createOnMeeting(OnMeetingDTO onMeetingDTO);
    public void createOnMeetingMem(OnMeetingMemDTO onMeetingMemDTO);
    public void joinOnMeeting(OnMeetingMemDTO onMeetingMemDTO);

    public OnMeetingDTO getOnMeetingById(Long onMeetingIdx);

    public int modifyOnMeeting(OnMeetingDTO onMeetingDTO);
    public int modifyOnMeetingPersonnel(OnMeetingDTO onMeetingDTO);

    public int removeOnMeeting(Long id);
    public List<OnMeetingDTO> searchOnMeeting(String searchKeyword, String category, String address);

//    public List<OnMeetingDTO> searchOnMeetingWithCate(String category, String address);
//
//    public List<OnMeetingDTO> searchOnMeetingWithAddr(String address);

    public List<OnMeetingDTO> getRecommendOnMeetingList(Long memberIdx);

    public List<OnMeetingDTO> getOnMeetingListByMember(Long memberIdx);

    public OnMeetingMemDTO getOnMeetingMemById(OnMeetingMemDTO onMeetingMemDTO);

    public int removeOnMeetingMem(OnMeetingMemDTO onMeetingMemDTO);
}
