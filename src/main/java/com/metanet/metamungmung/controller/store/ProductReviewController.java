package com.metanet.metamungmung.controller.store;

import com.metanet.metamungmung.dto.member.MemberDTO;
import com.metanet.metamungmung.dto.store.PatchProductReviewDTO;
import com.metanet.metamungmung.dto.store.ProductReviewDTO;
import com.metanet.metamungmung.security.JwtFilter;
import com.metanet.metamungmung.service.store.ProductReviewService;
import com.metanet.metamungmung.vo.store.GetReviewListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<GetReviewListVO> showReviewList(@PathVariable("productIdx") Long productIdx) {
        List<GetReviewListVO> reviewList = reviewService.getReviewList(productIdx);
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
     * [POST] /products/:productIdx/reviews
     * @return String
     **/
    @PostMapping("/{productIdx}/reviews")
    public String createReview(@PathVariable("productIdx") Long productIdx,
                               @RequestBody ProductReviewDTO productReviewDTO , HttpServletRequest request) {

        /* productReviewDTO 객체에 값을 넣어준다. */
        productReviewDTO.setProductIdx(productIdx);
        
        Long memberIdx = productReviewDTO.getMemberIdx();

        String result = "";


        /* 이미 해당 구매 상품에 대해 작성한 리뷰가 있는지 체크한다. */
        int idx1 = reviewService.checkReview(productIdx, memberIdx);

        if(idx1 >= 1) {
            result = "이미 작성한 리뷰가 존재합니다. ";
        } else {
            int idx2 = reviewService.registerReview(productReviewDTO);

            if (idx2 == 1) {
                result = "리뷰를 등록하였습니다.";
            }
        }

        return result;
    }

    /**
     * 리뷰 수정 API
     * [PATCH] /products/:productIdx/reviews/:reviewIdx
     * @return ProductReviewDTO
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

    /**
     * 리뷰 삭제 API
     * [POST] /products/:productIdx/reviews/:reviewIdx
     * @return String
     **/
    @PostMapping("/{productIdx}/reviews/{reviewIdx}")
    public String deleteReview(
            @PathVariable("productIdx") Long productIdx,
            @PathVariable("reviewIdx") Long reviewIdx) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Long memberIdx = 0L;

        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            MemberDTO memberDTO = (MemberDTO) userDetails;
            memberIdx = memberDTO.getMemberIdx();
        }

        System.out.println("pid : " + productIdx);
        System.out.println("rid : " + reviewIdx);
        System.out.println("mid : " + memberIdx);

        Map<String, Long> map = new HashMap<>();
        map.put("productIdx", productIdx);
        map.put("reviewIdx", reviewIdx);

        String result = "";

        int idx = reviewService.deleteReview(map);

        if(idx == 1) {
            result = "정상적으로 삭제되었습니다.";
        }

        return result;
    }
 }
