package com.metanet.metamungmung.dto.store;

import lombok.Data;

import java.util.Date;

@Data
public class PatchProductReviewDTO {
    private Long productReviewIdx;
    private Long productIdx;
    private String title;
    private String content;
    private Date updateDate;
}
