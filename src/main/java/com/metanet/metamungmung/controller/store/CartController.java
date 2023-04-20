package com.metanet.metamungmung.controller.store;

import com.metanet.metamungmung.dto.store.CartDTO;
import com.metanet.metamungmung.dto.store.CartProductDTO;
import com.metanet.metamungmung.service.store.CartService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {

    @Autowired
    private CartService service;

    // 장바구니 리스트 가져오기 ( 장바구니 안 전체 상품 )
    @GetMapping("")
    public List<CartProductDTO> getCartList(HttpSession session) throws Exception {
        // MemberDTO member = (MemberDTO) session.getAttribute("member");
        //  Long m_id = member.getM_id();
        // List<CartDTO> cartList = service.getMyCartList(m_id);

        System.out.println("###@@#$%$$%^%^%%^");
        Long memberIdx = 1L;
        return service.getMyCartList(memberIdx);
    }


    // 장바구니 상품 추가 , 상품 id 필요
    @PostMapping("")
    @ResponseBody
    public ResponseEntity<String> addProductToCart(HttpSession session, @RequestBody CartProductDTO cartProduct) throws Exception {
        //MemberDTO member = (MemberDTO) session.getAttribute("member");
        //Long memberIdx = member.getM_id();
         Long productIdx = cartProduct.getProductIdx();
         Long quantity = cartProduct.getQuantity();

        service.addProductToCart(cartProduct);

        CartDTO cart = new CartDTO();
        cart.setMemberIdx(1L);
        service.createCart(cart); // 장바구니 생성

        // 상품 정보 받아서 cartproduct 에 넣기
        CartProductDTO cartProductDTO = new CartProductDTO();
        cartProductDTO.setCartIdx(cart.getCartIdx());
        cartProductDTO.setProductIdx(productIdx);
        cartProductDTO.setQuantity(quantity);

//        if (service.checkCart(productIdx, 1L) > 0) {
//           // service.updateCount(cartProductDTO);
//        } else {
       //     service.addCart(cartProductDTO, 1L);
//        }
       // return "redirect:/cart/cartList";
        return ResponseEntity.ok("Product added to cart");
    }

//    @DeleteMapping("/cartDelete/{cartIdx}")
//    public String deleteCart(@PathVariable Long p_id, HttpSession session) throws Exception {
//        // Long m_id = (Long) session.getAttribute("member");
//        service.deleteCart(p_id, 1L);
//
//        return "redirect:/cart/cartList" + 1L;
//    }
//
//    @GetMapping("/cartDeleteAll")
//    public String deleteAllCart(HttpSession session) throws Exception{
//        Long m_id = (Long) session.getAttribute("member");
//        service.deleteAllCart(m_id);
//        // return "redirect: /cart/cartList" + m_id;
//        return "true";
//    }
//
//    @PatchMapping("/cartUpdate")
//    public String updateCart(HttpSession session, @RequestBody Map<String, Integer> productInfo) {
//        //  Long m_id = (Long) session.getAttribute("member");
//        Long p_id = Long.valueOf(productInfo.get("p_id"));
//        int quantity = productInfo.get("quantity");
//        service.updateCart(p_id, 1L, quantity);
//        return "redirect: /cart/cartUpdate";
//    }
}
