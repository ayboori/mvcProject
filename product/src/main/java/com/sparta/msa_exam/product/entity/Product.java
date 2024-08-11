package com.sparta.msa_exam.product.entity;

import com.fasterxml.jackson.annotation.JsonTypeId;
import com.sparta.msa_exam.product.dto.ProductRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor
public class Product {
    // auto increment Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    Integer supply_price;

    public Product(ProductRequestDto requestDto){
        this.name = requestDto.getName();
        this.supply_price = requestDto.getSupply_price();
    }
}
