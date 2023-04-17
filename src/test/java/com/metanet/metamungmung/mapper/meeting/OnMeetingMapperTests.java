package com.metanet.metamungmung.mapper.meeting;


import com.metanet.metamungmung.dto.meeting.OnMeetingDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class OnMeetingMapperTests {

    @Autowired
    private OnMeetingMapper onMeetingMapper;

    @Test
    public void createOnMeetingTest(){
        OnMeetingDTO dto = new OnMeetingDTO();
        dto.setOnMeetName("모임 이름");
        dto.setCategory("소형견");
        dto.setIsPublic("0");
        onMeetingMapper.createOnMeeting(dto);

        assertThat(dto.getOnMeetingIdx()).isNotNull();
    }

    @Test
    public void getOnMeetingByIdTest(){
        OnMeetingDTO dto = onMeetingMapper.getOnMeetingById(1L);

        assertThat(dto.getOnMeetingIdx()).isEqualTo(1L);
    }

}
