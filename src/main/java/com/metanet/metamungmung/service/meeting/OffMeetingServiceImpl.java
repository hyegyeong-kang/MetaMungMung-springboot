package com.metanet.metamungmung.service.meeting;

import com.metanet.metamungmung.dto.meeting.OffMeetingDTO;
import com.metanet.metamungmung.mapper.meeting.OffMeetingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OffMeetingServiceImpl implements OffMeetingService {
    @Autowired
    private OffMeetingMapper offMeetingMapper;

    @Override
    public List<OffMeetingDTO> getOffMeetingList() {
        return offMeetingMapper.getOffMeetingList();
    }
}
