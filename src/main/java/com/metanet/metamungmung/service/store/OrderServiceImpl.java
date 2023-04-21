package com.metanet.metamungmung.service.store;

import java.util.ArrayList;
import java.util.List;

import com.metanet.metamungmung.dto.store.OrderDetailDTO;
import com.metanet.metamungmung.mapper.member.MemberMapper;
import com.metanet.metamungmung.mapper.store.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metanet.metamungmung.dto.store.OrderDTO;
import com.metanet.metamungmung.dto.store.OrderProductDTO;
import com.metanet.metamungmung.dto.store.PaymentDTO;
import com.metanet.metamungmung.mapper.store.OrderMapper;
import com.metanet.metamungmung.mapper.store.PaymentMapper;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private PaymentMapper paymentMapper;

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private MemberMapper memberMapper;

	@Override
	public OrderDTO getOrderDetailList(Long orderIdx) {
		return orderMapper.getOrderDetail(orderIdx);
	}

	@Override
	public List<OrderDTO> getOrderList(Long memberIdx) {
		List<OrderDTO> list = orderMapper.getOrderList(memberIdx);
		list.forEach(order -> order.setOrderDetailList(orderMapper.getOrderDetail(order.getOrderIdx()).getOrderDetailList()));

		return list;
	}

	@Transactional
	@Override
	public void addOrder(OrderDTO order, PaymentDTO payment) {
		orderMapper.createOrder(order);
		orderMapper.createOrderDetail(order);
		payment.init();
		paymentMapper.createPayment(payment);
	}

	@Override
	public int cancelOrder(Long orderIdx) {
		return orderMapper.cancelOrder(orderIdx);
	}

	@Override
	public int confirmOrder(Long orderIdx, Long memberIdx) {

		if(orderMapper.confirmOrder(orderIdx) == 1){
			int accPoint = paymentMapper.getPayment(orderIdx).getAccPoint();
			memberMapper.accumulatePoint(accPoint, memberIdx);
		}
		return 0;
	}

	@Override
	public List<OrderProductDTO> getProductsInfo(List<OrderProductDTO> orderProducts) {
		List<OrderProductDTO> order = new ArrayList<>();

		for(OrderProductDTO product : orderProducts) {
			OrderProductDTO orderProduct = orderMapper.getProductInfo(product.getProductIdx());
			orderProduct.setOrderQuantity(product.getOrderQuantity());
			orderProduct.init();
			order.add(orderProduct);
		}

		return order;
	}

	@Override
	public List<OrderDetailDTO> getOrderDetailsInfo(List<OrderProductDTO> orderProducts) {
		List<OrderDetailDTO> list = new ArrayList<>();

		for(OrderProductDTO product : orderProducts) {
			OrderDetailDTO detailDto = new OrderDetailDTO();
			detailDto.setProductIdx(product.getProductIdx());
			detailDto.setProductDTO(productMapper.getProduct(product.getProductIdx()));
			detailDto.setQuantity(product.getOrderQuantity());
			list.add(detailDto);
		}

		return list;
	}

	@Override
	public PaymentDTO getPayment(Long orderIdx) {
		return paymentMapper.getPayment(orderIdx);
	}
}