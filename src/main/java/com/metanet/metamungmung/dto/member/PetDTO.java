package com.metanet.metamungmung.dto.member;

import lombok.Data;

import java.util.Date;

@Data
public class PetDTO {
    private Long petIdx;
    private Long memberIdx;
    private String animalRegistrationNo;
    private String petName;
    private String sex;
    private Date birth;
    private String petImg;
    private String status;
    private Date createDate;
    private Date updateDate;
}
