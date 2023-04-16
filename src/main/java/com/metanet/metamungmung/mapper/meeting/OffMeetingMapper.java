package com.metanet.metamungmung.mapper.meeting;

import com.metanet.metamungmung.dto.meeting.OffMeetingDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OffMeetingMapper {
    public List<OffMeetingDTO> getOffMeetingList();
}
