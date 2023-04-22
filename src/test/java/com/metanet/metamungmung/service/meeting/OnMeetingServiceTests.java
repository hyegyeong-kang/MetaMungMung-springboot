package com.metanet.metamungmung.service.meeting;

import com.metanet.metamungmung.dto.meeting.OnMeetingDTO;
import com.metanet.metamungmung.dto.meeting.OnMeetingMemDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class OnMeetingServiceTests {

    @Autowired
    private OnMeetingService onMeetingService;

    @Test
    public void createOnMeetingTest(){
        OnMeetingDTO onMeetingDTO = new OnMeetingDTO();
        OnMeetingMemDTO onMeetingMemDTO = new OnMeetingMemDTO();

        onMeetingDTO.setOnMeetName("온모임");
        onMeetingDTO.setCategory("일상");
        onMeetingDTO.setIntroduction("온모임입니다~^^");
        onMeetingDTO.setThumbnail("");
        onMeetingDTO.setIsPublic("0");
        onMeetingDTO.setOnMeetingAddr("집");

        onMeetingMemDTO.setMemberIdx(1L);
        onMeetingMemDTO.setIsHost("1");

        onMeetingService.createOnMeeting(onMeetingDTO, onMeetingMemDTO);

        log.info("createOnMeetingTest -> {}, {}", onMeetingDTO, onMeetingMemDTO);
    }

    @Test
    public void getOnMeetingByIdTest(){
        OnMeetingDTO onMeetingDTO = onMeetingService.getOnMeetingById(109L);

        log.info("getOnMeetingByIdTest -> {}", onMeetingDTO);
    }

    @Test
    public void modifyOnMeetingTest(){
        OnMeetingDTO onMeetingDTO = new OnMeetingDTO();
        onMeetingDTO.setOnMeetingIdx(104L);
        onMeetingDTO.setOnMeetName("크림");
        onMeetingDTO.setCategory("일상");
        onMeetingDTO.setIntroduction("크림아 멍멍해");
        onMeetingDTO.setIsPublic("0");
        onMeetingDTO.setOnMeetingAddr("용인");
        int result = onMeetingService.modifyOnMeeting(onMeetingDTO);

        log.info("modifyOnMeetingTest -> {}", result);
    }

    @Test
    public void modifyOnMeetingPersonnelTest(){
        OnMeetingDTO onMeetingDTO = new OnMeetingDTO();
        onMeetingDTO.setOnMeetingIdx(101L);
        onMeetingDTO.setPersonnel(100);
        int result = onMeetingService.modifyOnMeetingPersonnel(onMeetingDTO);

        log.info("modifyOnMeetingPersonnelTest -> {}", result);
    }

    @Test
    public void removeOnMeetingTest(){
//        OnMeetingMem 삭제
        OnMeetingMemDTO onMeetingMemDTO = new OnMeetingMemDTO();
        onMeetingService.removeOnMeetingMem(103L, 9L);

//        OnMeeting 삭제
        int result = onMeetingService.removeOnMeeting(103L, 9L);

        log.info("removeOnMeetingTest -> {}", result);
    }

    @Test
    public void searchOnMeetingTest(){
        List<OnMeetingDTO> onMeetList = onMeetingService.searchOnMeeting("비글", "", "");

        log.info("searchOnMeetingTest -> {}", onMeetList);
    }

    @Test
    public void getOnMeetingListByMemberTest(){
        List<OnMeetingDTO> onMeetList = onMeetingService.getOnMeetingListByMember(1L);

        log.info("getOnMeetingListByMemberTest -> {}", onMeetList);
    }

    @Test
    public void getRecommendOnMeetingListTest(){
        List<OnMeetingDTO> onMeetList = onMeetingService.getRecommendOnMeetingList(1L);

        log.info("getRecommendOnMeetingListTest -> {}", onMeetList);
    }
}
