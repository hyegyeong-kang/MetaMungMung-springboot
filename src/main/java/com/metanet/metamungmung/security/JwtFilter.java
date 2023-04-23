package com.metanet.metamungmung.security;

import com.metanet.metamungmung.service.member.MemberService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JwtFilter extends BasicAuthenticationFilter {

    @Autowired
    private MemberService memberService;

    public JwtFilter(AuthenticationManager authenticationManager, MemberService memberService) {
        super(authenticationManager);
        this.memberService = memberService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request2, HttpServletResponse response2, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest rq = (HttpServletRequest) request2;
        HttpServletResponse rp = (HttpServletResponse) response2;
        System.out.println("--------------------------혹시 시작도 안 되는 거니?");

        String requestURI = rq.getRequestURI();

        if (requestURI.equals("/members/idCheck")) {
            filterChain.doFilter(rq, rp); // 해당 URL은 토큰 검증을 수행하지 않음
            return;
        }

        if (requestURI.equals("/members/findId")) {
            filterChain.doFilter(rq, rp); // 해당 URL은 토큰 검증을 수행하지 않음
            return;
        }

        if (requestURI.equals("/products")) {
            filterChain.doFilter(rq, rp); // 해당 URL은 토큰 검증을 수행하지 않음
            return;
        }

        Pattern pattern = Pattern.compile("^/products/(?<productIdx>\\d+)$");
        Matcher matcher = pattern.matcher(requestURI);

        if (matcher.find()) {
            filterChain.doFilter(rq, rp); // 해당 URL은 토큰 검증을 수행하지 않음
            return;
        }

        pattern = Pattern.compile("^/products/(?<productIdx>\\d+)/reviews$");
        matcher = pattern.matcher(requestURI);

        if (matcher.find()) {
            filterChain.doFilter(rq, rp); // 해당 URL은 토큰 검증을 수행하지 않음
            return;
        }


        if (rq.getHeader("AUTHORIZATION") == null) {
            System.out.println("--------------------------혹시 없는거니?");
            return;
        } else {
            String authorizationHeader = rq.getHeader("AUTHORIZATION");
            String jwt = authorizationHeader.replace("Bearer", "");
            System.out.println("--------------------------나오니?");
            if (!isJwtValid((jwt))) {
                return;
            }
            // jwt에서 username 빼옴
            // loadUserByUsername을 이용
            String username = Jwts.parser().setSigningKey("secret").parseClaimsJws(jwt).getBody().getSubject();
            UserDetails userDetails = memberService.loadUserByUsername(username);

            if(userDetails != null) {
                UsernamePasswordAuthenticationToken  authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

                System.out.println("ddddddddddddddddddddddddddddddd" + authentication);

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request2));
                SecurityContextHolder.getContext().setAuthentication(authentication);

                filterChain.doFilter(request2, response2);

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
