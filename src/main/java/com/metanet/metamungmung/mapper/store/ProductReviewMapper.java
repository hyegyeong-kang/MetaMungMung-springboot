package com.metanet.metamungmung.mapper.store;

import com.metanet.metamungmung.dto.store.PatchProductReviewDTO;
import com.metanet.metamungmung.dto.store.ProductReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductReviewMapper {
    public List<ProductReviewDTO> getReviewList(Long productIdx);

    public ProductReviewDTO getReview(Long productIdx, Long reviewIdx);

    public int registerReview(ProductReviewDTO productReviewDTO);

    public int updateReview(PatchProductReviewDTO patchProductReviewDTO);
}
