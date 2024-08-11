package com.sparta.msa_exam.order.order;

import com.sparta.msa_exam.order.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
