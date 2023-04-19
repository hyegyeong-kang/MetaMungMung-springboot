package com.metanet.metamungmung.dto.store;

import lombok.Data;

import java.util.List;

@Data
public class CartDTO {
    private Long cartIdx;
    private Long memberIdx;
    private Long productIdx;
    private int quantity;
    private List<ProductDTO> productList;
}
