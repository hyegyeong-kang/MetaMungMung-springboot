package com.metanet.metamungmung.dto.store;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {
    private Long orderIdx;
    private Long memberIdx;
    private int orderPrice;
    private String deliveryMsg;
    private Date createDate;
    private Date updateDate;
    private String status;
    private int orderQuantity;

    private List<OrderDetailDTO> orderDetailList;
}