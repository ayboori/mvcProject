package com.sparta.msa_exam.order.dto;

import com.sparta.msa_exam.order.entity.Order;
import com.sparta.msa_exam.order.entity.OrderProduct;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class OrderResponseDto {
    private final Long id;
    private final String name;
    private final List<Long> productIds;

    public OrderResponseDto(Order order) {
        this.id = order.getOrder_id();
        this.name = order.getName();
        this.productIds = order.getOrderProducts().stream()
                .map(orderProduct ->
                        orderProduct.getProductId() != null ?
                                orderProduct.getProductId() :
                                -1L // null 값 있을 시 NullPointerException 방지를 위한 기본값
                )
                .collect(Collectors.toList());
    }
}
