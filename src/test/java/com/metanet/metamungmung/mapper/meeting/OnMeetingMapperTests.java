package com.metanet.metamungmung.mapper.meeting;


import com.metanet.metamungmung.dto.meeting.OnMeetingDTO;
import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

public class OnMeetingMapperTests {

    private OnMeetingMapper onMeetingMapper;

    @Test
    public void createOnMeetingTest(){
        OnMeetingDTO dto = new OnMeetingDTO();
        dto.setOnMeetName("모임 이름");
        dto.setCategory("소형견");
        dto.setIsPublic("0");
        onMeetingMapper.createOnMeeting(dto);
    }

}
