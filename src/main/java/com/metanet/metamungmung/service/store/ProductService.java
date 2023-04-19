package com.metanet.metamungmung.service.store;

import com.metanet.metamungmung.dto.store.ProductDTO;

import java.util.List;

public interface ProductService {
    public List<ProductDTO> getProductList();

    public ProductDTO getProduct(Long productIdx);
}
