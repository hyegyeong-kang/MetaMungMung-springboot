package com.metanet.metamungmung.controller.store;

import com.metanet.metamungmung.dto.store.CartDTO;
import com.metanet.metamungmung.dto.store.CartProductDTO;
import com.metanet.metamungmung.service.store.CartService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<CartDTO> getCartList(HttpSession session) throws Exception {
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
    public boolean addCart(HttpSession session, @RequestBody Map<String, Long> productInfo) throws Exception {
        //MemberDTO member = (MemberDTO) session.getAttribute("member");
        //Long memberIdx = member.getM_id();
        Long productIdx = Long.valueOf(productInfo.get("p_id"));
        Long quantity = productInfo.get("quantity");

        System.out.println("여기야!");

        CartProductDTO cartProductDTO = new CartProductDTO();
        cartProductDTO.setProductIdx(productIdx);
        cartProductDTO.setQuantity(quantity);

        CartDTO cartdto = new CartDTO();
        cartdto.setMemberIdx(1L);

        cartdto.getCartProductDTOList().add(cartProductDTO);

        service.addCart(cartdto);


      //  cart.setM_id(41L);
//        cart.setP_id(p_id);
//        cart.setQuantity(quantity);

        System.out.println("여기야");

//        if (service.checkCart(p_id, 41L) > 0) {
//            service.updateCount(cart);
//        } else {
//            service.addCart(cart);
//        }
        //return "redirect:/cart/cartList";
        return true;
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
