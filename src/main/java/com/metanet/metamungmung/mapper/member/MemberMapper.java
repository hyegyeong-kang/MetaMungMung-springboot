package com.metanet.metamungmung.mapper.member;

import com.metanet.metamungmung.dto.member.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    public List<MemberDTO> getList();
    MemberDTO findByUsername(String username);
    List<String> findAuthorities(String username);
    public void signUp(MemberDTO member);
}
