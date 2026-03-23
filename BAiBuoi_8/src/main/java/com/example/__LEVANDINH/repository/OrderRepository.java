package com.example.__LEVANDINH.repository;

import com.example.__LEVANDINH.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
