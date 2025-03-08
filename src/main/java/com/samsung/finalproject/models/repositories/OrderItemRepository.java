package com.samsung.finalproject.models.repositories;

import com.samsung.finalproject.models.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
