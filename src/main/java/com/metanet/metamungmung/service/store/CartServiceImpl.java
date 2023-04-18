package com.metanet.metamungmung.service.store;


import com.metanet.metamungmung.dto.store.CartDTO;
import com.metanet.metamungmung.dto.store.CartProductDTO;
import com.metanet.metamungmung.mapper.store.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private final CartMapper mapper;
    public CartServiceImpl(CartMapper mapper) {
        this.mapper = mapper;
    }


    // 해당 회원 장바구니 리스트 출력
    @Override
    public List<CartProductDTO> getMyCartList(Long memberIdx) {
        System.out.println( "KANG!!!"+ mapper.getMyCartList(1L));
        return mapper.getMyCartList(memberIdx);
    }

    // 장바구니 생성
    @Override
    public void createCart(CartDTO cart) {
        mapper.createCart(cart);
    }

    // 멤버 id 로 장바구니 가져오기
    @Override
    public CartDTO getCartByUserId(Long memberIdx) {
        return mapper.getCartByUserId(memberIdx);
    }




    // 카트 id 로 카트에 담긴 상품 가져오기
    @Override
    public CartDTO getCartProductsByCartId(Long cartIdx) {
        return mapper.getCartProductsByCartId(cartIdx);
    }


    // 해당 회원 장바구니에 상품 추가
    @Override
    public void addProductToCart(CartProductDTO cartProduct) {
       // Cart cart = cartMapper.getCartByUserId(cartProduct.getCart().getUserId());
//        CartDTO cart = mapper.getCartByUserId(memberIdx);
//        if (cart == null) {
//            cart = new CartDTO();
//            cart.setMemberIdx(memberIdx);
//            mapper.createCart(cart);
//        }
      //  cartProduct.setCart(cart);
        CartDTO cart = new CartDTO();
        //cart.setMemberIdx(cartProduct.ge);

        mapper.addProductToCart(cartProduct);


        //mapper.addCart(cart);
    }

    // 장바구니 중복 상품 확인
    @Override
    public int checkCart(Long p_id, Long m_id) {
        return mapper.checkCart(p_id, m_id);
    }

    // 중복된 상품이 있다면 넣지말고 수량 더해주기
    @Override
    public void updateCount(CartDTO cart) {
        mapper.updateCount(cart);
    }

}
