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

    @Override
    @Transactional
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
    public List<OnMeetingDTO> searchOnMeeting(String searchKeyword) {
//        if(category.equals("전체")){
//            return mapper.searchOnMeeting(searchKeyword);
//        }
//        return mapper.searchOnMeetingWithCategory(searchKeyword, category);
        return mapper.searchOnMeeting(searchKeyword);
    }


    @Override
    public List<OnMeetingDTO> getRecommendOnMeetingList(Long memberIdx) {
        return mapper.getRecommendOnMeetingList(memberIdx);
    }

//    @Override
//    public List<OnMeetingDTO> getRecommendOnMeetingListWithCategory(String memberIdx, String category) {
//        return mapper.getRecommendOnMeetingListWithCategory(memberIdx, category);
//    }

    @Override
    public List<OnMeetingDTO> getOnMeetingListByMember(Long memberIdx) {
        return mapper.getOnMeetingListByMember(memberIdx);
    }

    @Override
    public OnMeetingDTO joinOnMeeting(Long onMeetingIdx) {
//        OnMeetingDTO onMeetingDTO = new OnMeetingDTO();
//        onMeetingDTO.setOnMeetingIdx(onMeetingIdx);
//        if(onMeetingDTO.getPersonnel() > onMeetingDTO.getMemberCnt()){
//            OnMeetingMemDTO onMeetingMemDTO = new OnMeetingMemDTO();
//            onMeetingMemDTO.setOnMeetingIdx(onMeetingIdx);
//            onMeetingMemDTO.setMemberIdx(1L);
//            onMeetingMemDTO.setIsHost("0");
//
//            mapper.joinOnMeeting(onMeetingMemDTO);
//
//            return mapper.getOnMeetingById(onMeetingMemDTO.getOnMeetingIdx());
//        }
//        return null;
        OnMeetingMemDTO onMeetingMemDTO = new OnMeetingMemDTO();
        onMeetingMemDTO.setOnMeetingIdx(onMeetingIdx);
        onMeetingMemDTO.setMemberIdx(1L);
        onMeetingMemDTO.setIsHost("0");

        mapper.joinOnMeeting(onMeetingMemDTO);

        return mapper.getOnMeetingById(onMeetingMemDTO.getOnMeetingIdx());
    }

    @Override
    public OnMeetingMemDTO getOnMeetingMemById(OnMeetingMemDTO onMeetingMemDTO) {
        return mapper.getOnMeetingMemById(onMeetingMemDTO);
    }

    @Override
    @Transactional
    public int removeOnMeetingMem(Long onMeetingIdx) {
        OnMeetingMemDTO onMeetingMemDTO = new OnMeetingMemDTO();
        onMeetingMemDTO.setOnMeetingIdx(onMeetingIdx);
        onMeetingMemDTO.setMemberIdx(1L);

        onMeetingMemDTO = mapper.getOnMeetingMemById(onMeetingMemDTO);
        if(mapper.getOnMeetingById(onMeetingMemDTO.getOnMeetingIdx()).getMemberCnt() == 1){
            // 온모임 멤버 삭제 + 온모임 삭제
            if(mapper.removeOnMeetingMem(onMeetingMemDTO) == 1){
                return mapper.removeOnMeeting(onMeetingMemDTO.getOnMeetingIdx());
            }
        }
        return mapper.removeOnMeetingMem(onMeetingMemDTO);
    }
}
