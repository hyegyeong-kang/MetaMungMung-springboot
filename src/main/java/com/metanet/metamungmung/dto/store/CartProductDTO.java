package com.metanet.metamungmung.dto.store;

import lombok.Data;

@Data
public class CartProductDTO {
    private Long cartProductIdx;
    private Long productIdx;
    private Long cartIdx;
    private Long quantity;
    private ProductDTO productDTO;
}
