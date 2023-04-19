package com.metanet.metamungmung.service.store;

import com.metanet.metamungmung.dto.store.ProductDTO;
import com.metanet.metamungmung.mapper.store.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductDTO> getProductList() {
        return productMapper.getProductList();
    }

    @Override
    public ProductDTO getProduct(Long productIdx) {
        return productMapper.getProduct(productIdx);
    }

    @Override
    public List<ProductDTO> getSearchProductList(String keyword) {
        return productMapper.getSearchProductList(keyword);
    }

    @Override
    public List<ProductDTO> getCategoryProductList(String keyword) {
        return productMapper.getCategoryProductList(keyword);
    }

    @Override
    public List<ProductDTO> getCategorySearchProductList(String category, String keyword) {
        return productMapper.getCategorySearchProductList(category, keyword);
    }
}
