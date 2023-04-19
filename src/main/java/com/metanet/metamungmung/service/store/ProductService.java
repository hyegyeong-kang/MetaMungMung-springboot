package com.metanet.metamungmung.service.store;

import com.metanet.metamungmung.dto.store.ProductDTO;

import java.util.List;

public interface ProductService {
    public List<ProductDTO> getProductList();

    public ProductDTO getProduct(Long productIdx);

    public List<ProductDTO> getSearchProductList(String keyword);

    public List<ProductDTO> getCategoryProductList(String keyword);

    public List<ProductDTO> getCategorySearchProductList(String category, String keyword);
}
