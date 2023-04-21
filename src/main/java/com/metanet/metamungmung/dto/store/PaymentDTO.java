package com.metanet.metamungmung.dto.store;

import lombok.Data;

import java.util.Date;

@Data
public class PaymentDTO {
    private Long paymentIdx;
    private Long orderIdx;
    private int paymentPrice;
    private int usePoint;
    private String method;
    private Date createDate;
    private int accPoint;

    public void init() {
        this.accPoint = (int)(paymentPrice * 0.01);
    }
}