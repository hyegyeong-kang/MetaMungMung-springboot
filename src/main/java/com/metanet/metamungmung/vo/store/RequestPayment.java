package com.metanet.metamungmung.vo.store;

import com.metanet.metamungmung.dto.store.OrderProductDTO;
import lombok.Data;

import java.util.List;

@Data
public class RequestPayment {
    private int totalQuantity;
    private int totalPrice;
    private Long memberIdx;

    private List<OrderProductDTO> orderProductList;

    private String method;
    private int paymentPrice;
    private int usePoint;

    private String deliveryMsg;
}
