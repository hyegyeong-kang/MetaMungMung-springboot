package com.metanet.metamungmung.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO implements UserDetails {
    private Long memberIdx;
    private String memberId;
    private String password;
    private String memberName;
    private String sex;
    private Date birth;
    private String email;
    private String phone;
    private String address1;
    private String address2;
    private String authority;
    private String status;
    private int point;
    private String memberImg;
    private Date createDate;
    private Date updateDate;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(authority));
    }

    @Override
    public String getUsername() {
        return memberId;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status.equals("ACTIVE");
    }

//    public List<SimpleGrantedAuthority> getAuthorities() {
//        return authority.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//    }

}
