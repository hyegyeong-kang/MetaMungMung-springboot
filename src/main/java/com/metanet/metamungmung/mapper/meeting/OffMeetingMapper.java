package com.metanet.metamungmung.mapper.meeting;

import com.metanet.metamungmung.dto.meeting.OffMeetingDTO;
import com.metanet.metamungmung.dto.meeting.PatchOffMeetingDTO;
import com.metanet.metamungmung.vo.meeting.GetOffMeetingVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OffMeetingMapper {
    public List<OffMeetingDTO> getOffMeetingList();

    public OffMeetingDTO getOffMeeting(Long offMeetingIdx);

    public void registerOffMeeting(OffMeetingDTO offMeetingDTO);

    public List<GetOffMeetingVO> getOffMeetingMembers(Long offMeetingIdx);

    public int updateOffMeeting(PatchOffMeetingDTO patchOffMeetingDTO);
}
