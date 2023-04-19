package com.metanet.metamungmung.service.member;

import com.metanet.metamungmung.dto.member.MemberDTO;
import com.metanet.metamungmung.dto.member.PetDTO;
import com.metanet.metamungmung.mapper.member.MemberMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.Collections;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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

        logger.info("loadUserByUsername----------------"+member.getAuthority());

        return member;
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
    public int modify(MemberDTO member) {
        String password = member.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        member.setPassword(encodedPassword);
        return mapper.modify(member);
    }

    public MemberDTO getUserEmail(String email) {
        return mapper.getUserEmail(email);
    }

    @Override
    public List<PetDTO> getPetList() {
        return mapper.getPetList();
    }

    @Override
    public void register(PetDTO pet) {
        mapper.register(pet);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

//    private Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        authorities.add(new SimpleGrantedAuthority(member.getAuthority()));
//        return authorities;
//    }
}
