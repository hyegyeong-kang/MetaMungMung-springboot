package com.metanet.metamungmung.security;

import com.metanet.metamungmung.mapper.member.MemberMapper;
import com.metanet.metamungmung.service.member.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private MemberService memberService;
    private MemberMapper memberMapper;

    public SecurityConfig(MemberMapper memberMapper, MemberService memberService) {
        this.memberMapper = memberMapper;
        this.memberService = memberService;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/members/signup")
                .antMatchers("/members/idCheck");
        // 이 요청들에 대해서는 spring security 필터 체인을 적용하지 않겠다
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/members/**").hasAnyRole("MEMBER")
                .antMatchers("/onMeetings/**").hasAnyRole("MEMBER")
                .antMatchers("/offMeetings/**").hasAnyRole("MEMBER")
                .anyRequest().authenticated()
                .and()
                .addFilter(getAuthenticationFilter())
                .addFilter(JwtFilter()).authorizeRequests()
                .and()
                .formLogin()
                .and()
                .logout();
//                .and()
//                .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler());

    }


    @Bean
    public JwtFilter JwtFilter() throws Exception {
        return new JwtFilter(authenticationManager(), memberService);
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception{
        return new AuthenticationFilter(authenticationManager(), memberService);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    public class CustomAccessDeniedHandler implements AccessDeniedHandler {
//        @Override
//        public void handle(HttpServletRequest request, HttpServletResponse response,
//                           AccessDeniedException accessDeniedException) throws IOException, ServletException {
//
//            response.sendRedirect("http://localhost:8082/404"); // 에러 페이지 경로
//        }
//    }

}