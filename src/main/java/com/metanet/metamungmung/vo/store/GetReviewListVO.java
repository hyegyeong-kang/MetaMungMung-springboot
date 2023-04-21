package com.metanet.metamungmung.vo.store;

import lombok.Getter;

import java.util.Date;

@Getter
public class GetReviewListVO {
    private Long productReviewIdx;
    private Long productIdx;
    private String title;
    private String content;
    private Date createDate;
    private Date updateDate;
    private GetReviewMemberVO reviewMember;
}
