package com.metanet.metamungmung.mapper.store;

import com.metanet.metamungmung.dto.store.ProductReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductReviewMapper {
    public List<ProductReviewDTO> getReviewList(Long productIdx);
}
