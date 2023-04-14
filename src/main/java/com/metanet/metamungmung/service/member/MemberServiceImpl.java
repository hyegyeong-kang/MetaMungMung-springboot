package com.metanet.metamungmung.service.member;

import com.metanet.metamungmung.dto.member.MemberDTO;
import com.metanet.metamungmung.dto.member.PetDTO;
import com.metanet.metamungmung.mapper.member.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private MemberMapper mapper;

    public List<MemberDTO> getMemberList(){
        return mapper.getMemberList();
    }

    @Override
    public MemberDTO findByUserId(String memberId) {
        return mapper.findByUserId(memberId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberDTO member = mapper.findByUserId(username);
        if (member == null) {
            throw new UsernameNotFoundException("Member not found");
        }
        List<String> authorities = mapper.findAuthorities(username);
        return new User(member.getMemberId(), member.getPassword(), getAuthorities(authorities));
    }

    @Override
    public void signUp(MemberDTO member){
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        System.out.println(member);
        mapper.signUp(member);
    }

    @Override
    public int idCheck(String memberId){
        return mapper.idCheck(memberId);
    }

    @Override
    public MemberDTO login(MemberDTO member) {
        MemberDTO foundMember = mapper.findByUserId(member.getMemberId());

        if (foundMember != null) {

            if (!passwordEncoder.matches(member.getPassword(), foundMember.getPassword())) {
                return null;
            }
            return mapper.login(foundMember);
        }

        return null;
    }

    @Override
    public int modify(MemberDTO member) {
        String password = member.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        member.setPassword(encodedPassword);
        return mapper.modify(member);
    }

    @Override
    public List<PetDTO> getPetList() {
        return null;
    }

    @Override
    public void register(PetDTO pet) {

    }

    private Collection<? extends GrantedAuthority> getAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
