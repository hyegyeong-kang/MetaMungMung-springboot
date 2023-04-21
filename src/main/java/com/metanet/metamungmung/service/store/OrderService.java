package com.metanet.metamungmung.service.store;

import com.metanet.metamungmung.dto.store.OrderDTO;
import com.metanet.metamungmung.dto.store.OrderDetailDTO;
import com.metanet.metamungmung.dto.store.OrderProductDTO;
import com.metanet.metamungmung.dto.store.PaymentDTO;

import java.util.List;


public interface OrderService {

	public OrderDTO getOrderDetailList(Long orderIdx);

	public List<OrderDTO> getOrderList(Long memberIdx);

	public PaymentDTO getPayment(Long orderIdx);

	public List<OrderProductDTO> getProductsInfo(List<OrderProductDTO> orderProducts);

	public List<OrderDetailDTO> getOrderDetailsInfo(List<OrderProductDTO> orderProducts);

	public void addOrder(OrderDTO order, PaymentDTO payment);

	public int cancelOrder(Long orderIdx);

	public int confirmOrder(Long orderIdx, Long memberIdx);

}