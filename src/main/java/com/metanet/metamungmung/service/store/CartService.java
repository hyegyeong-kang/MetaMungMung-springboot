package com.metanet.metamungmung.service.store;

import com.metanet.metamungmung.dto.store.CartDTO;
import com.metanet.metamungmung.dto.store.CartProductDTO;

import java.util.List;

public interface CartService {
//    // 해당 회원 장바구니 리스트 출력
//    public List<CartDTO> getMyCartList(Long memberIdx);
//
//    // 장바구니 생성
//    public void createCart(CartDTO cart);
//
//    // 멤버id 로 장바구니 가져오기
//    public CartDTO getCartByUserId(Long memberIdx);
//
//    // 카트 id 로 카드에 담긴 상품 가져오기
//    public CartDTO getCartProductsByCartId(Long cartIdx);
//
//    // 해당 회원 장바구니 추가
//    public void addProductToCart(CartProductDTO cartProduct);
//
//    // 장바구니 중복 상품 확인 (1보다 크면 해당 상품 장바구니에 있는것)
//    public int checkCart(Long p_id, Long m_id);
//
//    // 중복된 상품이 있다면 넣지말고 수량 더해주기
//    public void updateCount(CartProductDTO cartProduct);
// 해당 회원 장바구니 리스트 출력
public List<CartDTO> getMyCartList(Long memberIdx);

    // 해당 회원 장바구니 추가
    public void addCart(CartDTO cart);

    // 장바구니 중복 상품 확인 (1보다 크면 해당 상품 장바구니에 있는것)
    public int checkCart(Long productIdx, Long memberIdx);

    // 중복된 상품이 있다면 넣지말고 수량 더해주기
    public void updateCount(CartDTO cart);

    // 장바구니 물건 삭제
    public void deleteCart(Long productIdx, Long memberIdx);

    // 장바구니 전체 비우기
    public void deleteAllCart(Long memberIdx);

    // 장바구니 물건 업데이트 (장바구니에서 수량변경하는 것)
    public void updateCart(Long productIdx, Long memberIdx, int quantity, Long cartIdx);

    // 해당 회원의 장바구니 전체 금액 출력
    public Long getTotalPrice(Long memberIdx);
}
