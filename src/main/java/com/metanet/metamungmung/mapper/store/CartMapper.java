package com.metanet.metamungmung.mapper.store;

import com.metanet.metamungmung.dto.store.CartDTO;
import com.metanet.metamungmung.dto.store.CartProductDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CartMapper {
//    // 해당 회원 장바구니 리스트 출력
//    public List<CartProductDTO> getMyCartList(Long memberIdx);
//
//    // 장바구니 생성
//    public void createCart(CartDTO cart);
//
//    // 장바구니 물건 추가
//    public void addProductToCart(CartProductDTO cartProduct);
//
//    // 멤버 id 로 장바구니 가져오기
//    public CartDTO getCartByUserId(Long memberIdx);
//
//    // 카트 id 로 해당
//    public CartDTO getCartProductsByCartId(Long cartIdx);
//
//
//    // 장바구니 중복 상품 확인
//    public int checkCart(@Param("productIdx")Long productIdx, @Param("memberIdx")Long memberIdx);
//
//    // 중복된 상품이 있다면 넣지말고 수량 더해주기
//    public void updateCount(CartProductDTO cartProduct);
//
//
//
////    // 장바구니 물건 삭제
////    public void deleteCart(@Param("p_id")Long p_id, @Param("m_id")Long m_id);
////
////    // 장바구니 전체 비우기
////    public void deleteAllCart(Long m_id);
////
////    // 장바구니 물건 업데이트 (장바구니에서 수량변경하는 것)
////    public void updateCart(@Param("p_id")Long p_id, @Param("m_id")Long m_id, @Param("quantity")int quantity);
////
////    // 해당 회원의 장바구니 전체 금액 출력
////    public Long getTotalPrice(Long m_id);

    // 해당 회원 장바구니 리스트 출력
    List<CartDTO> getMyCartList(Long memberIdx);

    // 해당 회원 장바구니 물건추가
    public void addCart(CartDTO cart);

    // 장바구니 중복 상품 확인
    public int checkCart(@Param("productIdx")Long productIdx, @Param("memberIdx")Long memberIdx);

    // 중복된 상품이 있다면 넣지말고 수량 더해주기
    public void updateCount(CartDTO cart);

    // 장바구니 물건 삭제
    public void deleteCart(@Param("productIdx")Long productIdx, @Param("memberIdx")Long memberIdx);

    // 장바구니 전체 비우기
    public void deleteAllCart(Long memberIdx);

    // 장바구니 물건 업데이트 (장바구니에서 수량변경하는 것)
    public void updateCart(@Param("productIdx")Long productIdx, @Param("memberIdx")Long memberIdx, @Param("quantity")int quantity, @Param("cartIdx")Long cartIdx);

    // 해당 회원의 장바구니 전체 금액 출력
    public Long getTotalPrice(Long memberIdx);

}
