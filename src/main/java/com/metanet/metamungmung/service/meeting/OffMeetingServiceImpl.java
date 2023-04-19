package com.metanet.metamungmung.service.meeting;

import com.metanet.metamungmung.dto.meeting.OffMeetingDTO;
import com.metanet.metamungmung.dto.meeting.PatchOffMeetingDTO;
import com.metanet.metamungmung.vo.meeting.GetOffMeetingVO;
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

    @Override
    public OffMeetingDTO getOffMeeting(Long offMeetingIdx) {
        return offMeetingMapper.getOffMeeting(offMeetingIdx);
    }

    @Override
    public void registerOffMeeting(OffMeetingDTO offMeetingDTO) {
        offMeetingMapper.registerOffMeeting(offMeetingDTO);
    }

    @Override
    public List<GetOffMeetingVO> getOffMeetingMembers(Long offMeetingIdx) {
        return offMeetingMapper.getOffMeetingMembers(offMeetingIdx);
    }

    @Override
    public int updateOffMeeting(PatchOffMeetingDTO patchOffMeetingDTO) {
        return offMeetingMapper.updateOffMeeting(patchOffMeetingDTO);
    }

    @Override
    public int deleteOffMeeting(Long offMeetingIdx) {
        return offMeetingMapper.deleteOffMeeting(offMeetingIdx);
    }
}
