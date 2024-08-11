package com.sparta.msa_exam.order.order;

import com.sparta.msa_exam.order.ProductClient;
import com.sparta.msa_exam.order.dto.OrderProductRequestDto;
import com.sparta.msa_exam.order.dto.OrderRequestDto;
import com.sparta.msa_exam.order.dto.OrderResponseDto;
import com.sparta.msa_exam.order.entity.Order;
import com.sparta.msa_exam.order.entity.OrderProduct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductClient productClient;
    private final OrderRespository orderRespository;
    private final OrderProductRepository orderProductRepository;

    // 주문 생성
    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto requestDto) {
        Order order = new Order(requestDto);
        return new OrderResponseDto(orderRespository.save(order));
    }

    // 주문에 상품 추가
    @Transactional
    public OrderResponseDto addProduct(OrderProductRequestDto requestDto, Long orderId) {

        // 주문이 존재하는지 확인
        Order order = orderRespository.findById(orderId)
                .orElseThrow(
                        ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "상품이 존재하지 않습니다.")
                );

        // 상품이 실제로 존재하는지 확인
        String productInfo = productClient.getProduct(requestDto.getProductId().toString());
        if (productInfo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "상품이 존재하지 않습니다.");
        }

        // 중간 테이블에 추가
        OrderProduct orderProduct = new OrderProduct(order,requestDto.getProductId());
        // 중간 테이블 내용을 주문 테이블에 추가
        order.addProduct(orderProduct);

        orderProductRepository.save(orderProduct);
        return new OrderResponseDto(order);
    }

    public String getProductInfo(String productId) {
        return productClient.getProduct(productId);
    }

    public OrderResponseDto getOrder(Long orderId) {
        // 주문이 존재하는지 확인
        Order order = orderRespository.findById(orderId)                .orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "상품이 존재하지 않습니다.")
        );

        return new OrderResponseDto(order);
    }
}