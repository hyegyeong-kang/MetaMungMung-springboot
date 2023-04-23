package com.metanet.metamungmung.mapper.meeting;


import com.metanet.metamungmung.aop.LoggingAspect;
import com.metanet.metamungmung.dto.meeting.OnMeetingDTO;
import com.metanet.metamungmung.dto.meeting.OnMeetingMemDTO;
import com.metanet.metamungmung.dto.member.MemberDTO;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Slf4j
public class OnMeetingMapperTests {

    @Autowired
    private OnMeetingMapper onMeetingMapper;

    @Test
    public void createOnMeetingTest(){
        OnMeetingDTO onMeetingDTO = new OnMeetingDTO();
        OnMeetingMemDTO onMeetingMemDTO = new OnMeetingMemDTO();

//        OnMeeting 생성
        onMeetingDTO.setOnMeetName("모임 이름");
        onMeetingDTO.setCategory("소형견");
        onMeetingDTO.setIsPublic("0");
        onMeetingMapper.createOnMeeting(onMeetingDTO);

//        OnMeetingMem 생성
        onMeetingMemDTO.setOnMeetingIdx(onMeetingDTO.getOnMeetingIdx());
        onMeetingMemDTO.setMemberIdx(1L);
        onMeetingMemDTO.setIsHost("1");
        onMeetingMapper.createOnMeetingMem(onMeetingMemDTO);

        log.info("createOnMeetingTest -> {} {}", onMeetingDTO, onMeetingMemDTO);
    }

    @Test
    public void joinOnMeetingTest(){
        OnMeetingMemDTO onMeetingMemDTO = new OnMeetingMemDTO();
        onMeetingMemDTO.setOnMeetingIdx(10L);
        onMeetingMemDTO.setMemberIdx(1L);
        onMeetingMemDTO.setIsHost("0");
        onMeetingMapper.joinOnMeeting(onMeetingMemDTO);

        log.info("joinOnMeetingTest -> {}", onMeetingMemDTO);
    }

    @Test
    public void removeOnMeetingMemTest(){
        OnMeetingMemDTO onMeetingMemDTO = new OnMeetingMemDTO();
        onMeetingMemDTO.setOnMeetingMemIdx(127L);
        int result = onMeetingMapper.removeOnMeetingMem(onMeetingMemDTO);

        log.info("removeOnMeetingMemTest -> {}", result);
    }

    @Test
    public void modifyOnMeetingTest(){
        OnMeetingDTO onMeetingDTO = new OnMeetingDTO();
        onMeetingDTO.setOnMeetingIdx(101L);
        onMeetingDTO.setOnMeetName("크림이");
        onMeetingDTO.setCategory("일상");
        onMeetingDTO.setIntroduction("크림아 멍멍해");
        onMeetingDTO.setIsPublic("0");
        onMeetingDTO.setOnMeetingAddr("용인");
        int result = onMeetingMapper.modifyOnMeeting(onMeetingDTO);

        log.info("modifyOnMeetingTest -> {}", result);
    }

    @Test
    public void modifyOnMeetingPersonnelTest(){
        OnMeetingDTO onMeetingDTO = new OnMeetingDTO();
        onMeetingDTO.setOnMeetingIdx(101L);
        onMeetingDTO.setPersonnel(100);
        int result = onMeetingMapper.modifyOnMeetingPersonnel(onMeetingDTO);

        log.info("modifyOnMeetingPersonnelTest -> {}", result);
    }

    @Test
    public void removeOnMeetingTest(){
//        OnMeetingMem 삭제
        OnMeetingMemDTO onMeetingMemDTO = new OnMeetingMemDTO();
        onMeetingMemDTO.setOnMeetingMemIdx(122L);
        onMeetingMapper.removeOnMeetingMem(onMeetingMemDTO);

//        OnMeeting 삭제
        int result = onMeetingMapper.removeOnMeeting(102L);

        log.info("removeOnMeetingTest -> {}", result);
    }

    @Test
    public void getOnMeetingByIdTest(){
        OnMeetingDTO dto = onMeetingMapper.getOnMeetingById(1L);

        log.info("getOnMeetingByIdTest -> {}", dto);
    }

    @Test
    public void searchOnMeetingTest(){
        List<OnMeetingDTO> onMeetList = onMeetingMapper.searchOnMeeting("비글", "", "");

        log.info("searchOnMeetingTest -> {}", onMeetList);
    }

    @Test
    public void getOnMeetingListByMemberTest(){
        List<OnMeetingDTO> onMeetList = onMeetingMapper.getOnMeetingListByMember(1L);

        log.info("getOnMeetingListByMemberTest -> {}", onMeetList);
    }

    @Test
    public void getRecommendOnMeetingListTest(){
        List<OnMeetingDTO> onMeetList = onMeetingMapper.getRecommendOnMeetingList(1L);

        log.info("getRecommendOnMeetingListTest -> {}", onMeetList);
    }

}
