package com.metanet.metamungmung.service.member;

import com.metanet.metamungmung.dto.member.MemberDTO;
import com.metanet.metamungmung.dto.member.PetDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MemberService extends UserDetailsService {

    //Member 부분
    public List<MemberDTO> getMemberList();

    MemberDTO findByUserId(String memberId);

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException;

    public void signUp(MemberDTO member);

    public int idCheck(String memberId);

    public int modify(MemberDTO member);

    public MemberDTO getUserEmail(String email);

    //Pet 부분
    public List<PetDTO> getPetList();
    public void register(PetDTO pet);


}
