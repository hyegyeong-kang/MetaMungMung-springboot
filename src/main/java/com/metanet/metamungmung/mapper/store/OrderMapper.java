package com.metanet.metamungmung.mapper.store;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.metanet.metamungmung.dto.store.OrderDTO;
import com.metanet.metamungmung.dto.store.OrderProductDTO;

@Mapper
public interface OrderMapper {

	public OrderDTO getOrder(Long orderIdx);

	public List<OrderDTO> getOrderList(Long memberIdx);

	public OrderDTO getOrderDetail(Long orderIdx);

	public OrderProductDTO getProductInfo(Long productIdx);

	public void createOrderDetail(OrderDTO order);

	public void createOrder(OrderDTO order);

	public int cancelOrder(Long orderIdx);

	public int confirmOrder(Long orderIdx);
}