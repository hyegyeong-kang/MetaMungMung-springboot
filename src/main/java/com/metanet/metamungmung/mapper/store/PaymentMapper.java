package com.metanet.metamungmung.mapper.store;

import com.metanet.metamungmung.dto.store.PaymentDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {
	
	public void createPayment(PaymentDTO payment);
	
	public PaymentDTO getPayment(Long orderIdx);
}
