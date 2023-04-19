package com.metanet.metamungmung.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.Base64;
import java.util.Collections;
import java.util.Map;


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

    public long getMemberIdxFromToken(String token) {
        String[] tokenArr = token.split("\\.");
        String payload = tokenArr[1];
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String decodedPayload = new String(decoder.decode(payload));

        // JSON 형태의 문자열을 Map으로 변환합니다.
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> payloadMap = null;
        try {
            payloadMap = objectMapper.readValue(decodedPayload, new TypeReference<Map<String, Object>>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // "memberIdx" 키의 값을 Long 타입으로 반환합니다.
        return ((Number)payloadMap.get("memberIdx")).longValue();
    }

}
