package com.metanet.metamungmung.dto.member;

import lombok.Data;

import java.util.Date;

@Data
public class MemberDTO {
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

}
