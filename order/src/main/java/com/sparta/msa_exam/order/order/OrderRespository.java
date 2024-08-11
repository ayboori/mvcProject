package com.sparta.msa_exam.order.order;

import com.sparta.msa_exam.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRespository extends JpaRepository<Order, Long> {
}
