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
     * [GET] /products/search?keyword={keyword}
     * @return List<ProductDTO>
     **/
    @GetMapping("/search")
    public List<ProductDTO> searchProductList(@RequestParam(name = "keyword") String keyword) {
        List<ProductDTO> productList = productService.getSearchProductList(keyword);
        return productList;
    }

    /**
     * 카테고리별 상품 검색 API
     * [GET] /products/category?keyword={keyword}
     * @return List<ProductDTO>
     **/
    @GetMapping("/category")
    public List<ProductDTO> categoryProductList(@RequestParam(name = "keyword") String keyword) {
        List<ProductDTO> productList = productService.getCategoryProductList(keyword);
        return productList;
    }

    /**
     * 카테고리 및 상품 검색 API
     * [GET] /products/categorySearch?category={category}&keyword={keyword}
     * @return List<ProductDTO>
     **/
    @GetMapping("/categorySearch")
    public List<ProductDTO> categorySearchProductList(@RequestParam(name = "category") String category ,@RequestParam(name = "keyword") String keyword) {

        List<ProductDTO> productList = productService.getCategorySearchProductList(category, keyword);
        return productList;
    }
}
