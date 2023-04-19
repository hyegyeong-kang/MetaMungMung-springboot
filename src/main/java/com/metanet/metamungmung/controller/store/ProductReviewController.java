package com.metanet.metamungmung.controller.store;

import com.metanet.metamungmung.dto.store.ProductReviewDTO;
import com.metanet.metamungmung.service.store.ProductReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductReviewController {
    @Autowired
    private final ProductReviewService reivewService;

    public ProductReviewController(ProductReviewService reivewService) {
        this.reivewService = reivewService;
    }

    /**
     * 리뷰 조회 API
     * [GET] /products/{productIdx}/reviews
     * @return List<ProductReviewDTO>
     **/
    public List<ProductReviewDTO> showReviewList() {
        return null;
    }
}
