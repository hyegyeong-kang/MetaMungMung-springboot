package com.metanet.metamungmung.mapper.member;

import com.metanet.metamungmung.dto.member.MemberDTO;
import com.metanet.metamungmung.dto.member.PetDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    public List<MemberDTO> getMemberList();

    MemberDTO getMemberInfo(Long memberIdx);

    MemberDTO findMemberId(String email);

    MemberDTO findMemberPW(String memberId, String email);

    public MemberDTO findByUserId(String memberId);

    List<String> findAuthorities(String authority);

    public void signUp(MemberDTO member);

    public int idCheck(String memberId);

    public int modify(MemberDTO member);

    public MemberDTO getUserEmail(String email);

    //Pet 부분
    public List<PetDTO> getPetList();
    public void register(PetDTO pet);

}
