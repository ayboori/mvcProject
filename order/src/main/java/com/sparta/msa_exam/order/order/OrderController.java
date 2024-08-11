package com.sparta.msa_exam.order.order;

import com.sparta.msa_exam.order.dto.OrderProductRequestDto;
import com.sparta.msa_exam.order.dto.OrderRequestDto;
import com.sparta.msa_exam.order.dto.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    // POST /order 주문 추가 API
    @PostMapping
    public OrderResponseDto createOrder(@RequestBody OrderRequestDto orderRequestDto){
        return orderService.createOrder(orderRequestDto);
    }

    // PUT /order/{orderId}  주문에 상품을 추가하는 API
    @PutMapping("/{orderId}")
    public OrderResponseDto addProduct(
            @RequestBody OrderProductRequestDto requestDto,
            @PathVariable Long orderId
            ){
        return orderService.addProduct(requestDto,orderId);
    }

    // GET /order/{orderId}  주문 단건 조회 API
    @GetMapping("/order/{orderId}")
    public OrderResponseDto getOrder(@PathVariable Long orderId) {
        return orderService.getOrder(orderId);
    }

}