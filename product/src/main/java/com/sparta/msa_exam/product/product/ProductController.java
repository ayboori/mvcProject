package com.sparta.msa_exam.product.product;

import com.sparta.msa_exam.product.dto.ProductRequestDto;
import com.sparta.msa_exam.product.dto.ProductResponseDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Value("${server.port}") // 애플리케이션이 실행 중인 포트를 주입받습니다.
    private String serverPort;

    // 상품 추가 API
    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto productRequestDto) {
        // 헤더에 서버 포트 정보 추가
        HttpHeaders headers = new HttpHeaders();
        headers.set("Server-Port", serverPort);
        return new ResponseEntity<>(productService.createProduct(productRequestDto),headers, HttpStatus.CREATED);
    }

    // 상품 전체 목록 조회 API
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getProducts() {
        // 헤더에 서버 포트 정보 추가
        HttpHeaders headers = new HttpHeaders();
        headers.set("Server-Port", serverPort);
        return new ResponseEntity<>(productService.getProducts(),headers, HttpStatus.OK);
    }

    // +) product 정보 돌려주는 API
    @GetMapping("/{id}")
    public ProductResponseDto getProduct(@PathVariable("id") Long id) {
        return productService.getProductDetails(id);
    }
}