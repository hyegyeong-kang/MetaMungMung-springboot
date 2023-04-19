package com.metanet.metamungmung.controller.store;

import com.metanet.metamungmung.dto.store.ProductReviewDTO;
import com.metanet.metamungmung.service.store.ProductReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductReviewController {
    @Autowired
    private final ProductReviewService reviewService;

    public ProductReviewController(ProductReviewService reviewService) {
        this.reviewService = reviewService;
    }

    /**
     * 리뷰 조회 API
     * [GET] /products/{productIdx}/reviews
     * @return List<ProductReviewDTO>
     **/
    @GetMapping("/{productIdx}/reviews")
    public List<ProductReviewDTO> showReviewList(@PathVariable("productIdx") Long productIdx) {
        List<ProductReviewDTO> reviewList = reviewService.getReviewList(productIdx);
        return reviewList;
    }
}
