package com.sparta.msa_exam.order.dto;

import com.sparta.msa_exam.order.entity.Order;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderProductRequestDto {
    private Long orderId;
    private Long productId;
}
