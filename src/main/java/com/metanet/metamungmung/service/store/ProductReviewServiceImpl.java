package com.metanet.metamungmung.service.store;

import com.metanet.metamungmung.dto.store.ProductReviewDTO;
import com.metanet.metamungmung.mapper.store.ProductReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductReviewServiceImpl implements ProductReviewService {
    @Autowired
    private ProductReviewMapper reviewMapper;

    @Override
    public List<ProductReviewDTO> getReviewList(Long productIdx) {
        return reviewMapper.getReviewList(productIdx);
    }

    @Override
    public ProductReviewDTO getReview(Long productIdx, Long reviewIdx) {
        return reviewMapper.getReview(productIdx, reviewIdx);
    }

    @Override
    public int registerReview(ProductReviewDTO productReviewDTO) {
        return reviewMapper.registerReview(productReviewDTO);
    }
}
