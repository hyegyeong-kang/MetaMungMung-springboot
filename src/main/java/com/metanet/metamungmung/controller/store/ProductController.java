package com.metanet.metamungmung.controller.store;

import com.metanet.metamungmung.dto.store.ProductDTO;
import com.metanet.metamungmung.service.store.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * 상품 조회 API
     * [GET] /products
     * @return List<ProductDTO>
     **/
    @GetMapping("")
    public List<ProductDTO> showProductList() {
        List<ProductDTO> productList = productService.getProductList();
        return productList;
    }

    /**
     * 상품 상세 조회 API
     * [GET] /products/:productIdx
     * @return ProductDTO
     **/
    @GetMapping("/{productIdx}")
    public ProductDTO showProductDetail(@PathVariable Long productIdx) {
        ProductDTO product = productService.getProduct(productIdx);
        return product;
    }

    /**
     * 상품 검색 API
     * [POST] /products/search?keyword={keyword}
     * @return List<ProductDTO>
     **/
    @GetMapping("/search")
    public List<ProductDTO> searchProductList(@RequestParam(name = "keyword") String keyword) {
        List<ProductDTO> productList = productService.getSearchProductList(keyword);
        return productList;
    }
}
