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
    /*
    1.  `FeignClient`를 이용해서 주문 서비스에 상품 서비스 클라이언트 연결
    2. 상품 목록 조회 API를 호출해서 파라미터로 받은 `product_id` 가 상품 목록에 존재하는지 검증
    3. 존재할경우 주문에 상품을 추가하고, 존재하지 않는다면 주문에 저장하지 않음
     */
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

    public OrderResponseDto getOrder(Long orderId) {
        // 주문이 존재하는지 확인
        Order order = orderRespository.findById(orderId)                .orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "상품이 존재하지 않습니다.")
        );

        return new OrderResponseDto(order);
    }
}