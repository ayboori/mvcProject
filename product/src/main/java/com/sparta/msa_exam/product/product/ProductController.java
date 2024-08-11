package com.sparta.msa_exam.product.product;

import com.sparta.msa_exam.product.dto.ProductRequestDto;
import com.sparta.msa_exam.product.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

//    @Value("${server.port}") // 애플리케이션이 실행 중인 포트를 주입받습니다.
//    private String serverPort;

    // 상품 추가 API
    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto){

        return productService.createProduct(productRequestDto);
    }

    // 상품 전체 목록 조회 API
    @GetMapping("/products")
    public List<ProductResponseDto> getProducts() {
        return productService.getProducts();
    }
}