package com.anw.order.service.domain.ports.output.repository;

import com.anw.order.service.domain.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    List<Order> findAll(int page, int size);
    Order save (Order order);
    Order getById(Order order);
    Optional<Order> findById(String orderId);
}
