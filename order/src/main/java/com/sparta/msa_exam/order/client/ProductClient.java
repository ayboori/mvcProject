package com.sparta.msa_exam.order.client;

import com.sparta.msa_exam.order.dto.ProductResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// FeignClient 인터페이스를 작성하여 Product 서비스 호출을 수행
@FeignClient(name = "product-service")
public interface ProductClient {

    // endpoint의 product id값을 가져옴
    @GetMapping("/product/{id}")
    ProductResponseDto getProduct(@PathVariable("id") String id);
}
