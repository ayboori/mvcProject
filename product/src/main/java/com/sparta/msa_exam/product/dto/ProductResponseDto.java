package com.sparta.msa_exam.product.dto;

import com.sparta.msa_exam.product.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductResponseDto {
    private Long id;

    String name;

    Integer supply_price;

    public ProductResponseDto(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.supply_price = product.getSupply_price();
    }
}
