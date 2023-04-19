package com.metanet.metamungmung.service.store;

import com.metanet.metamungmung.dto.store.ProductReviewDTO;

import java.util.List;

public interface ProductReviewService {
    public List<ProductReviewDTO> getReviewList(Long productIdx);
}
