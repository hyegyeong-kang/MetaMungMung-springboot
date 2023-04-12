package com.metanet.metamungmung.service.member;

import com.metanet.metamungmung.dto.member.MemberDTO;
import com.metanet.metamungmung.mapper.member.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MemberMapper mapper;

    public List<MemberDTO> getList(){
        return mapper.getList();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberDTO member = mapper.findByUsername(username);
        if (member == null) {
            throw new UsernameNotFoundException("User not found");
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

    private Collection<? extends GrantedAuthority> getAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
