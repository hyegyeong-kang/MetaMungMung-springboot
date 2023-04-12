package com.metanet.metamungmung.service.member;

import com.metanet.metamungmung.dto.member.MemberDTO;
import com.metanet.metamungmung.mapper.member.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface MemberService extends UserDetailsService {

    public List<MemberDTO> getList();

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException;

    public void signUp(MemberDTO member);

}
