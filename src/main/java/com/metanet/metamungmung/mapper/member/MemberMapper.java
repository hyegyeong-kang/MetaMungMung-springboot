package com.metanet.metamungmung.mapper.member;

import com.metanet.metamungmung.dto.member.MemberDTO;
import com.metanet.metamungmung.dto.member.PetDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    public List<MemberDTO> getMemberList();

    MemberDTO getMemberInfo(Long memberIdx);

    public MemberDTO findByUserId(String memberId);

    public String findId(String email);

    public void signUp(MemberDTO member);

    public int idCheck(String memberId);

    public int modify(MemberDTO member);

    public int withdrawal(MemberDTO member);

    public int updateAuth(MemberDTO member);

    public MemberDTO getUserEmail(String email);

    //Pet 부분
    public List<PetDTO> getPetList(Long memberIdx);
    public void register(PetDTO pet);
    public int delete(PetDTO pet);


    // 포인트 적립
    public int accumulatePoint(int point, Long memberIdx);

}
