package com.metanet.metamungmung.controller.store;

import com.metanet.metamungmung.dto.store.CartProductDTO;
import com.metanet.metamungmung.service.store.CartService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {

    @Autowired
    private CartService service;

    @GetMapping("")
    public List<CartProductDTO> getCartList(HttpSession session) throws Exception {
        // MemberDTO member = (MemberDTO) session.getAttribute("member");
        //  Long m_id = member.getM_id();
        // List<CartDTO> cartList = service.getMyCartList(m_id);

        Long memberIdx = 1L;
        return service.getMyCartList(memberIdx);
    }


//    @PostMapping("/cartAdd")
//    @ResponseBody
//    public boolean addCart(HttpSession session, @RequestBody Map<String, Integer> productInfo) throws Exception {
//        //MemberDTO member = (MemberDTO) session.getAttribute("member");
//        //Long m_id = member.getM_id();
//
//        CartProductDTO cart = new CartProductDTO();
//
//        Long p_id = Long.valueOf(productInfo.get("p_id"));
//        int quantity = productInfo.get("quantity");
//
//      //  cart.setM_id(41L);
//        cart.setP_id(p_id);
//        cart.setQuantity(quantity);
//
//        System.out.println("여기야");
//
//        if (service.checkCart(p_id, 41L) > 0) {
//            service.updateCount(cart);
//        } else {
//            service.addCart(cart);
//        }
//        //return "redirect:/cart/cartList";
//        return true;
//    }
//
//    @DeleteMapping("/cartDelete")
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
