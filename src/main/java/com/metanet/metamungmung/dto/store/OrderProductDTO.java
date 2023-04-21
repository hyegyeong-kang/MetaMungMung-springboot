package com.metanet.metamungmung.dto.store;

import java.util.List;

import lombok.Data;

@Data
public class OrderProductDTO {
	/* 뷰에서 전달받을 값 */
	private Long productIdx;
	private int orderQuantity;

	/* DB에서 읽어올 값 */
	private String brand;
	private String productName;
	private Long price;
	private String productImg;

	/* 계산이 필요한 값 */
	private int orderPrice;
	private int point;

	private List<OrderProductDTO> orderProductList;

	public void init() {
		this.orderPrice = this.price.intValue() * this.orderQuantity;
		this.point = (int)(orderPrice * 0.01);
	}
}