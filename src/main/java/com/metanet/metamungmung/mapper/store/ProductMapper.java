package com.metanet.metamungmung.mapper.store;

import com.metanet.metamungmung.dto.store.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    public List<ProductDTO> getProductList();

    public ProductDTO getProduct(Long productIdx);

    public List<ProductDTO> getSearchProductList(String keyword);

    public List<ProductDTO> getCategoryProductList(String keyword);

    public List<ProductDTO> getCategorySearchProductList(String category, String keyword);
}
