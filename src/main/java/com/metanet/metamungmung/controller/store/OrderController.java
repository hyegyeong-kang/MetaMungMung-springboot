package com.metanet.metamungmung.controller.store;

import com.metanet.metamungmung.dto.store.OrderDTO;
import com.metanet.metamungmung.dto.store.OrderDetailDTO;
import com.metanet.metamungmung.dto.store.OrderProductDTO;
import com.metanet.metamungmung.dto.store.PaymentDTO;
import com.metanet.metamungmung.service.store.CartService;
import com.metanet.metamungmung.service.store.OrderService;
import com.metanet.metamungmung.vo.store.RequestPayment;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private OrderService service;

    private CartService cService;

    /* 주문내역 보기 */
    @GetMapping("")
    public List<OrderDTO> getOrderList() {

//		MemberDTO member = (MemberDTO) session.getAttribute("member");
//		Long m_id = member.getM_id();
        Long memberIdx = 1L;

        return service.getOrderList(memberIdx);
    }

    /* 주문 상세 보기(+ 결제 정보) */
    @GetMapping("/{o_id}")
    public ResponseEntity<Map<String, Object>> getOrderDetail( @PathVariable("orderIdx") Long orderIdx) {

        OrderDTO orderDTO = service.getOrderDetailList(orderIdx);
        PaymentDTO paymentDTO = service.getPayment(orderIdx);

        Map<String, Object> response = new HashMap<>();
        response.put("order", orderDTO);
        response.put("payment", paymentDTO);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /* 주문서 */
    @PostMapping("")
    public List<OrderProductDTO> orderProduct(@RequestBody OrderProductDTO orderProduct) {

        List<OrderProductDTO> orderProducts = orderProduct.getOrderProductList();

        return service.getProductsInfo(orderProducts);
    }

    /* 결제하기 */
    @PostMapping("/payment")
    public ResponseEntity<Map<String, Object>> payment(@RequestBody RequestPayment requestPayment) {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        OrderDTO order = mapper.map(requestPayment, OrderDTO.class);
        OrderProductDTO orderProduct = mapper.map(requestPayment, OrderProductDTO.class);
        PaymentDTO payment = mapper.map(requestPayment, PaymentDTO.class);

//		MemberDTO member = (MemberDTO) session.getAttribute("member");
//		Long m_id = member.getM_id();
        Long memberIdx = 1L;
        order.setMemberIdx(memberIdx);
        List<OrderProductDTO> orderProducts = orderProduct.getOrderProductList();
//		order.setPrice(payment.getPayment_amount() + payment.getUsePoint());

//		List<OrderDetailDTO> list = new ArrayList<>();
////		int total_quantity = 0;
//		for(OrderProductDTO product : orderProducts) {
////			OrderDetailDTO detailDto = new OrderDetailDTO();
////			detailDto.setP_id(product.getP_id());
//			OrderDetailDTO detailDto = mapper.map(product, OrderDetailDTO.class);
//			detailDto.setProductDTO(pService.get(product.getP_id()));
//			detailDto.setQuantity(product.getQuantity());
//			list.add(detailDto);
////			total_quantity += product.getQuantity();
//		}
        List<OrderDetailDTO> list = service.getOrderDetailsInfo(orderProducts);
//		order.setTotal_amount(total_quantity); // 전달할 때 보내면 더 좋을듯
        order.setOrderDetailList(list);

        //여기 오류나!!!!!
//        payment.setM_id(m_id);
        service.addOrder(order, payment);

        for(OrderDetailDTO orderDetail : order.getOrderDetailList()) {
//            cService.deleteCart(orderDetailail.getProductIdx(), order.getMemberIdx());
        }

//		order = service.getOrderDetailList(order.getO_id());

        Map<String, Object> response = new HashMap<>();
        response.put("order", order);
        response.put("payment", payment);
        response.put("deliveryMsg", requestPayment.getDeliveryMsg());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /* 주문 취소 */
    @PatchMapping("/{orderIdx}")
    public ResponseEntity<Map<String, Object>> cancelOrder(@PathVariable("orderIdx") Long orderIdx) {

        Map<String, Object> response = new HashMap<>();
        OrderDTO orderDTO = null;
        PaymentDTO paymentDTO = null;

        if(service.cancelOrder(orderIdx) == 1){
            orderDTO = service.getOrderDetailList(orderIdx);
            paymentDTO = service.getPayment(orderIdx);
        }

        response.put("order", orderDTO);
        response.put("payment", paymentDTO);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
