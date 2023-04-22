package com.metanet.metamungmung.mapper.store;

import com.metanet.metamungmung.dto.store.PatchProductReviewDTO;
import com.metanet.metamungmung.dto.store.ProductReviewDTO;
import com.metanet.metamungmung.vo.store.GetReviewListVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductReviewMapper {
    public List<GetReviewListVO> getReviewList(Long productIdx);

    public ProductReviewDTO getReview(Long productIdx, Long reviewIdx);

    public int registerReview(ProductReviewDTO productReviewDTO);

    public int updateReview(PatchProductReviewDTO patchProductReviewDTO);

    public int deleteReview(Map<String, Long> map);

    public int checkReview(Long productIdx, Long memberIdx);
}
