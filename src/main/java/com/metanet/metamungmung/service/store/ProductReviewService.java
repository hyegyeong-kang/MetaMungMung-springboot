package com.metanet.metamungmung.service.store;

import com.metanet.metamungmung.dto.store.PatchProductReviewDTO;
import com.metanet.metamungmung.dto.store.ProductReviewDTO;
import com.metanet.metamungmung.vo.store.GetReviewListVO;

import java.util.List;
import java.util.Map;

public interface ProductReviewService {
    public List<GetReviewListVO> getReviewList(Long productIdx);

    public ProductReviewDTO getReview(Long productIdx, Long reviewIdx);

    public int registerReview(ProductReviewDTO productReviewDTO);

    public int updateReview(PatchProductReviewDTO patchProductReviewDTO);

    public int deleteReview(Map<String, Long> map);

    public int checkReview(Long productIdx, Long memberIdx);
}
