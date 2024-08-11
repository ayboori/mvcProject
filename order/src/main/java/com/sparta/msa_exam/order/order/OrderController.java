package com.sparta.msa_exam.order.order;

import com.sparta.msa_exam.order.dto.OrderProductRequestDto;
import com.sparta.msa_exam.order.dto.OrderRequestDto;
import com.sparta.msa_exam.order.dto.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Value("${server.port}") // 애플리케이션이 실행 중인 포트를 주입받습니다.
    private String serverPort;


    // POST /order 주문 추가 API
    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto orderRequestDto){
        // 헤더에 서버 포트 정보 추가
        HttpHeaders headers = new HttpHeaders();
        headers.set("Server-Port", serverPort);
        return new ResponseEntity<>(orderService.createOrder(orderRequestDto),headers, HttpStatus.CREATED);
    }

    // PUT /order/{orderId}  주문에 상품을 추가하는 API
    @PutMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> addProduct(
            @RequestBody OrderProductRequestDto requestDto,
            @PathVariable Long orderId){
        // 헤더에 서버 포트 정보 추가
        HttpHeaders headers = new HttpHeaders();
        headers.set("Server-Port", serverPort);
        return new ResponseEntity<>(orderService.addProduct(requestDto,orderId),headers, HttpStatus.OK);
    }

    // GET /order/{orderId}  주문 단건 조회 API
    @GetMapping("/order/{orderId}")
    public ResponseEntity<OrderResponseDto> getOrder(@PathVariable Long orderId) {
        // 헤더에 서버 포트 정보 추가
        HttpHeaders headers = new HttpHeaders();
        headers.set("Server-Port", serverPort);
        return new ResponseEntity<>(orderService.getOrder(orderId),headers, HttpStatus.OK);
    }

}