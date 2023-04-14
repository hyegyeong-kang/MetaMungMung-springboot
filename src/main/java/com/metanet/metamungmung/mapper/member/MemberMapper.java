package com.metanet.metamungmung.mapper.member;

import com.metanet.metamungmung.dto.member.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    public List<MemberDTO> getMemberList();
    public MemberDTO findByUserId(String memberId);
    List<String> findAuthorities(String authority);
    public void signUp(MemberDTO member);
    public MemberDTO login(MemberDTO member);
    public int idCheck(String memberId);
    public int modify(MemberDTO member);

}
