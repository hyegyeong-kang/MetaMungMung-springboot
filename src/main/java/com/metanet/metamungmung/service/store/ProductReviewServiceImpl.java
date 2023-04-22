package com.metanet.metamungmung.service.store;

import com.metanet.metamungmung.dto.store.PatchProductReviewDTO;
import com.metanet.metamungmung.dto.store.ProductReviewDTO;
import com.metanet.metamungmung.mapper.store.ProductReviewMapper;
import com.metanet.metamungmung.vo.store.GetReviewListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductReviewServiceImpl implements ProductReviewService {
    @Autowired
    private ProductReviewMapper reviewMapper;

    @Override
    public List<GetReviewListVO> getReviewList(Long productIdx) {
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

    @Override
    public int updateReview(PatchProductReviewDTO patchProductReviewDTO) {
        return reviewMapper.updateReview(patchProductReviewDTO);
    }

    @Override
    public int deleteReview(Map<String, Long> map) {
        return reviewMapper.deleteReview(map);
    }

    @Override
    public int checkReview(Long productIdx, Long memberIdx) {
        return reviewMapper.checkReview(productIdx, memberIdx);
    }
}
