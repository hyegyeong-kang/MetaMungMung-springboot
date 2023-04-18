package com.metanet.metamungmung.service.store;

import com.metanet.metamungmung.dto.store.CartDTO;
import com.metanet.metamungmung.dto.store.CartProductDTO;

import java.util.List;

public interface CartService {
    // 해당 회원 장바구니 리스트 출력
    public List<CartDTO> getMyCartList(Long memberIdx);

    // 해당 회원 장바구니 추가
    public void addCart(CartDTO cart);
}
