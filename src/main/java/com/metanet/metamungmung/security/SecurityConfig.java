package com.metanet.metamungmung.security;

import com.metanet.metamungmung.mapper.member.MemberMapper;
import com.metanet.metamungmung.service.member.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
//                        .antMatchers("/**")
                .antMatchers("/members")
                .antMatchers("/members/signup")
                .antMatchers("/members/idCheck");
        // 이 요청들에 대해서는 spring security 필터 체인을 적용하지 않겠다
    }

    @Override

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/**").permitAll()
            .antMatchers("/members").permitAll()
            .antMatchers("/members/idCheck").permitAll()
            .antMatchers("/members/modify/{memberIdx}", "/members/pets/register").hasAnyRole("MEMBER", "DOGOWNER")
            .anyRequest().authenticated()
            .and()
            .addFilter(getAuthenticationFilter())
            .addFilter(JwtFilter()).authorizeRequests()
            .and()
            .formLogin()
            .and()
            .logout();

    }


    @Bean
    public JwtFilter JwtFilter() throws Exception {
        return new JwtFilter(authenticationManager(), memberMapper);
    }

    @Bean
    public AuthenticationFilter getAuthenticationFilter() throws Exception{
        return new AuthenticationFilter(authenticationManager(), memberMapper);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}