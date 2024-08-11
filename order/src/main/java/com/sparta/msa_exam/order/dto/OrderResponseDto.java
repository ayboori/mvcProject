package com.sparta.msa_exam.order.dto;

import com.sparta.msa_exam.order.entity.Order;
import com.sparta.msa_exam.order.entity.OrderProduct;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class OrderResponseDto {
    private final Long id;
    private final String name;
    private List<Long> product_ids = new ArrayList<>(); // 빈 리스트로 초기화

    public OrderResponseDto(Order order) {
        this.id = order.getOrder_id();
        this.name = order.getName();
        if (order.getOrderProducts() != null) {
            this.product_ids = order.getOrderProducts().stream()
                    .map(OrderProduct::getProductId)
                    .collect(Collectors.toList());
        }
    }
}
