package com.metanet.metamungmung.dto.store;

import lombok.Data;

import java.util.Date;

@Data
public class ProductReviewDTO {
    private Long productReviewIdx;
    private Long productIdx;
    private Long memberIdx;
    private String title;
    private String content;
    private Date createDate;
    private Date updateDate;
    private String status;
}
