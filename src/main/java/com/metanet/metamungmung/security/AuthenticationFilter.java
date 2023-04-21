package com.metanet.metamungmung.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metanet.metamungmung.dto.member.MemberDTO;
import com.metanet.metamungmung.service.member.MemberService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private MemberService memberService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public AuthenticationFilter(AuthenticationManager authenticationManager, MemberService memberService) {
        super.setAuthenticationManager(authenticationManager);
        this.memberService = memberService;
        setFilterProcessesUrl("/members/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            MemberDTO memberDTO = new ObjectMapper().readValue(request.getInputStream(), MemberDTO.class);
            logger.info("-----------------"+memberDTO.getMemberId());
            logger.info("-----------------"+memberDTO.getPassword());
            if(memberDTO != null) {
                logger.info("-------------------확인--------------");
                return getAuthenticationManager().authenticate(
                        new UsernamePasswordAuthenticationToken(memberDTO.getMemberId(), memberDTO.getPassword(), new ArrayList<>())
                );
            } else {
                throw new UsernameNotFoundException("존재하는 아이디가 없음");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("여기가 넘어와야 해요--------------------------");
        MemberDTO member = (MemberDTO) authResult.getPrincipal();
        String username = member.getUsername();
        response.addHeader("memberIdx", member.getMemberIdx().toString());
        response.addHeader("status", member.getStatus());
        String token = Jwts.builder()
                .setHeaderParam("type", "token")
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() +1*(1000*60*60*24*365)))
                .signWith(SignatureAlgorithm.HS512, "secret")
                .compact();

        response.addHeader("token", token);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed");
    }

}
