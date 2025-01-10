package com.anw.order.service.domain;

import com.anw.order.service.domain.entity.Order;
import com.anw.order.service.domain.event.OrderCreatedEvent;
import com.anw.order.service.domain.event.OrderUpdatedEvent;
import com.anw.order.service.domain.ports.output.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class OrderCreateHelper {
    private final OrderDomainService orderDomainService;
    private final OrderRepository orderRepository;

    public OrderCreateHelper(OrderDomainService orderDomainService,
                             OrderRepository orderRepository) {
        this.orderDomainService = orderDomainService;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public OrderCreatedEvent persistCreateOrder(Order order) {
        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order, null);
        saveOrder(order);
        log.info("initialize order with id: {}", order.getId().getValue());
        return orderCreatedEvent;
    }

    private void saveOrder(Order order) {
        Order orderResult = orderRepository.save(order);
        log.info("order is saved with id: {}", orderResult.getId().getValue());
    }
}
