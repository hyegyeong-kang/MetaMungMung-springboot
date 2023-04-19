package com.metanet.metamungmung.controller.store;

import com.metanet.metamungmung.dto.store.PatchProductReviewDTO;
import com.metanet.metamungmung.dto.store.ProductReviewDTO;
import com.metanet.metamungmung.security.JwtFilter;
import com.metanet.metamungmung.service.store.ProductReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductReviewController {
    @Autowired
    private final ProductReviewService reviewService;
    @Autowired
    public JwtFilter jwtFilter;

    public ProductReviewController(ProductReviewService reviewService, JwtFilter jwtFilter) {
        this.reviewService = reviewService;
        this.jwtFilter = jwtFilter;
    }

    /**
     * 리뷰 조회 API
     * [GET] /products/:productIdx/reviews
     * @return List<ProductReviewDTO>
     **/
    @GetMapping("/{productIdx}/reviews")
    public List<ProductReviewDTO> showReviewList(@PathVariable("productIdx") Long productIdx) {
        List<ProductReviewDTO> reviewList = reviewService.getReviewList(productIdx);
        return reviewList;
    }

    /**
     * 리뷰 상세 조회(특정 리뷰 조회) API
     * [GET] /products/:productIdx/reviews/:reviewIdx
     * @return List<ProductReviewDTO>
     **/
    @GetMapping("/{productIdx}/reviews/{reviewIdx}")
    public ProductReviewDTO showReview (
            @PathVariable("productIdx") Long productIdx, @PathVariable("reviewIdx") Long reviewIdx) {

        ProductReviewDTO review = reviewService.getReview(productIdx, reviewIdx);
        return review;
    }

    /**
     * 리뷰 작성 API
     * [POST] /products/:productIdx}/reviews
     * @return String
     **/
    @PostMapping("/{productIdx}/reviews")
    public String createReview(@PathVariable("productIdx") Long productIdx,
                               @RequestBody ProductReviewDTO productReviewDTO , HttpServletRequest request) {

        /* Jwt로부터 memberIdx 추출 */
//        String token = request.getHeader("Authorization").substring(7);
//        System.out.println("token!!!!!!!!!!!!! : " + token);
//        Long memberIdx = jwtFilter.getMemberIdxFromToken(token);
//
//        System.out.println("memberIdx!!!!!!!!!!!! : " + memberIdx);

        Long memberIdx = 10L;

        /* productReviewDTO 객체에 값을 넣어준다. */
        productReviewDTO.setProductIdx(productIdx);
        productReviewDTO.setMemberIdx(memberIdx);

        int idx = reviewService.registerReview(productReviewDTO);

        String result = "";

        if (idx == 1) {
            result = "리뷰를 등록하였습니다.";
        }

        return result;
    }

    /**
     * 리뷰 수정 API
     * [PATCH] /products/:productIdx/reviews/:reviewIdx
     * @return String
     **/
    @PatchMapping("/{productIdx}/reviews/{reviewIdx}")
    public ProductReviewDTO modifyReview(
            @PathVariable("productIdx") Long productIdx,
            @PathVariable("reviewIdx") Long reviewIdx,
            @RequestBody PatchProductReviewDTO patchProductReviewDTO) {

        ProductReviewDTO productReviewDTO = new ProductReviewDTO();

        patchProductReviewDTO.setProductReviewIdx(reviewIdx);
        patchProductReviewDTO.setProductIdx(productIdx);

        /* 작성자인지 확인하는 로직 필요 */

        int idx = reviewService.updateReview(patchProductReviewDTO);

        if (idx == 1) {
            productReviewDTO = reviewService.getReview(productIdx, reviewIdx);
        }
        return productReviewDTO;
    }
 }
