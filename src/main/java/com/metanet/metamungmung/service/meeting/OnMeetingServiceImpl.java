package com.metanet.metamungmung.service.meeting;

import com.metanet.metamungmung.dto.meeting.OnMeetingDTO;
import com.metanet.metamungmung.dto.meeting.OnMeetingMemDTO;
import com.metanet.metamungmung.mapper.meeting.OnMeetingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OnMeetingServiceImpl implements OnMeetingService {
    @Autowired
    private OnMeetingMapper mapper;

    @Transactional
    @Override
    public OnMeetingDTO createOnMeeting(OnMeetingDTO onMeetingDTO, OnMeetingMemDTO onMeetingMemDTO){
        mapper.createOnMeeting(onMeetingDTO);
        onMeetingMemDTO.setIsHost("1");
        mapper.createOnMeetingMem(onMeetingMemDTO);

        return mapper.getOnMeetingById(onMeetingDTO.getOnMeetingIdx());
    }

    @Override
    public OnMeetingDTO getOnMeetingById(Long onMeetingIdx){
        return mapper.getOnMeetingById(onMeetingIdx);
    }

    @Override
    public int modifyOnMeeting(OnMeetingDTO onMeetingDTO) {
        return mapper.modifyOnMeeting(onMeetingDTO);
    }

    @Override
    public int modifyOnMeetingPersonnel(OnMeetingDTO onMeetingDTO) {
        return mapper.modifyOnMeetingPersonnel(onMeetingDTO);
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

    @Override
    public OnMeetingMemDTO joinOnMeeting(OnMeetingMemDTO onMeetingMemDTO) {
        mapper.createOnMeetingMem(onMeetingMemDTO);

        return mapper.getOnMeetingMemById(onMeetingMemDTO);
    }

    @Override
    public OnMeetingMemDTO getOnMeetingMemById(OnMeetingMemDTO onMeetingMemDTO) {
        return mapper.getOnMeetingMemById(onMeetingMemDTO);
    }

    @Override
    public int removeOnMeetingMem(OnMeetingMemDTO onMeetingMemDTO) {
        if(mapper.getOnMeetingById(onMeetingMemDTO.getOnMeetingIdx()).getMemberCnt() == 1){
            // 온모임 멤버 삭제 + 온모임 삭제
        }
        return mapper.removeOnMeetingMem(onMeetingMemDTO);
    }
}
