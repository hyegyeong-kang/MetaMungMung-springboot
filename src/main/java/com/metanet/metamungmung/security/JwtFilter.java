package com.metanet.metamungmung.security;

import com.metanet.metamungmung.dto.member.MemberDTO;
import com.metanet.metamungmung.mapper.member.MemberMapper;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JwtFilter extends BasicAuthenticationFilter {

    private MemberMapper memberMapper;

    public JwtFilter(AuthenticationManager authenticationManager, MemberMapper memberMapper) {
        super(authenticationManager);
        this.memberMapper = memberMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request2, HttpServletResponse response2, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest rq = (HttpServletRequest) request2;
        HttpServletResponse rp = (HttpServletResponse) response2;
        if (rq.getHeader("AUTHORIZATION") == null) {
            return;
        } else {
            String authorizationHeader = rq.getHeader("AUTHORIZATION");
            String jwt = authorizationHeader.replace("Bearer", "");
            if (!isJwtValid((jwt))) {
                return;
            }
            String name = Jwts.parser().setSigningKey("secret").parseClaimsJws(jwt).getBody().getSubject();
            MemberDTO memberDTO = memberMapper.findByUserId(name);

            if(memberDTO != null) {
                UserDetails user = User.builder()
                        .username(memberDTO.getMemberId())
                        .password(memberDTO.getPassword())
                        .authorities(new SimpleGrantedAuthority(memberDTO.getAuthority()))
                        .build();

                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        user,
                        memberDTO.getPassword(),
                        Collections.singleton(new SimpleGrantedAuthority(memberDTO.getAuthority())));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                if (authentication != null && authentication.isAuthenticated() && authentication.getAuthorities().stream()
                        .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_MEMBER"))) {
                    filterChain.doFilter(request2, response2);
                }
                else {
                    response2.setStatus(HttpServletResponse.SC_FORBIDDEN);
                }
            } else {
                throw new UsernameNotFoundException("Member not found");
            }
        }
    }

    public boolean isJwtValid(String jwt) {
        boolean returnValue = true;
        String subject = null;
        try {
            subject = Jwts.parser().setSigningKey("secret").parseClaimsJws(jwt).getBody().getSubject();
            System.out.println("subject : " + subject);
        }catch (Exception e){
            returnValue=false;
        }
        if(subject==null || subject.isEmpty()){
            returnValue = false;
        }
        return returnValue;
    }
}
