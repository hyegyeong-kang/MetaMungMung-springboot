package com.metanet.metamungmung.dto.store;

import lombok.Data;

import java.util.Date;

@Data
public class ProductReviewImgDTO {
    private Long productReviewImgIdx;
    private Long productReviewIdx;
    private String fileName;
    private String uploadPath;
    private Date createDate;
    private Date updateDate;
    private String status;
}
