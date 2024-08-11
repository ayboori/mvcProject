package com.sparta.msa_exam.order.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders_products")

// order-product 매핑하는 클래스
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    // 주문만 생성한 뒤 상품 추가할 수도 있으므로 nullable = true
    private Long productId;

    public OrderProduct (Order order, Long productId){
        this.order = order;
        this.productId = productId;
    }
}