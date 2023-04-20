package com.metanet.metamungmung.service.store;

import com.metanet.metamungmung.dto.store.CartDTO;
import com.metanet.metamungmung.dto.store.CartProductDTO;

import java.util.List;

public interface CartService {
    // 해당 회원 장바구니 리스트 출력
    public List<CartProductDTO> getMyCartList(Long memberIdx);

    // 장바구니 생성
    public void createCart(CartDTO cart);

    // 멤버id 로 장바구니 가져오기
    public CartDTO getCartByUserId(Long memberIdx);

    // 카트 id 로 카드에 담긴 상품 가져오기
    public CartDTO getCartProductsByCartId(Long cartIdx);

    // 해당 회원 장바구니 추가
    public void addProductToCart(CartProductDTO cartProduct);

    // 장바구니 중복 상품 확인 (1보다 크면 해당 상품 장바구니에 있는것)
    public int checkCart(Long p_id, Long m_id);

    // 중복된 상품이 있다면 넣지말고 수량 더해주기
    public void updateCount(CartDTO cart);
}
