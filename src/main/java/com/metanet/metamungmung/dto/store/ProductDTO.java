package com.metanet.metamungmung.dto.store;

import lombok.Data;

@Data
public class ProductDTO {
    private Long productIdx;
    private String category;
    private String productName;
    private String brand;
    private Long price;
    private String productImg;
    private String productDetail;
    private String productDetailImg;
    private String volume;
}
