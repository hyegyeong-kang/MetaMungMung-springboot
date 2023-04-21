package com.metanet.metamungmung.dto.store;

import lombok.Data;

@Data
public class OrderDetailDTO {
	private Long orderDetailIdx;
	private Long orderIdx;
	private Long productIdx;
	private int quantity;
	
	private ProductDTO productDTO;
}
