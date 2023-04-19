package com.metanet.metamungmung.service.meeting;

import com.metanet.metamungmung.dto.meeting.OffMeetingDTO;
import com.metanet.metamungmung.dto.meeting.OffMeetingMemDTO;
import com.metanet.metamungmung.dto.meeting.PatchOffMeetingDTO;
import com.metanet.metamungmung.vo.meeting.GetOffMeetingVO;
import com.metanet.metamungmung.mapper.meeting.OffMeetingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public int joinOffMeeting(OffMeetingMemDTO offMeetingMemDTO) {
        return offMeetingMapper.joinOffMeeting(offMeetingMemDTO);
    }

    @Override
    public int registerOffMeetingHost(OffMeetingMemDTO offMeetingMemDTO) {
        return offMeetingMapper.registerOffMeetingHost(offMeetingMemDTO);
    }

    @Override
    public OffMeetingMemDTO checkMemberByMemberIdx(Map<String, Long> map) {
        return offMeetingMapper.checkMemberByMemberIdx(map);
    }

    @Override
    public int cancelJoinOffMeeting(Map<String, Long> map) {
        return offMeetingMapper.cancelJoinOffMeeting(map);
    }
}
