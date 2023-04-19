package com.metanet.metamungmung.service.store;

import com.metanet.metamungmung.dto.store.PatchProductReviewDTO;
import com.metanet.metamungmung.dto.store.ProductReviewDTO;

import java.util.List;
import java.util.Map;

public interface ProductReviewService {
    public List<ProductReviewDTO> getReviewList(Long productIdx);

    public ProductReviewDTO getReview(Long productIdx, Long reviewIdx);

    public int registerReview(ProductReviewDTO productReviewDTO);

    public int updateReview(PatchProductReviewDTO patchProductReviewDTO);
}
