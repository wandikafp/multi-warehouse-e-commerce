package com.anw.order.service.domain;

import com.anw.domain.valueobject.UserId;
import com.anw.order.service.domain.dto.create.CreateOrderCommand;
import com.anw.order.service.domain.entity.Order;
import com.anw.order.service.domain.event.OrderCreatedEvent;
import com.anw.order.service.domain.event.OrderUpdatedEvent;
import com.anw.order.service.domain.ports.output.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.anw.domain.DomainConstants.UTC;

@Slf4j
@Component
public class OrderHelper {
    private final OrderDomainService orderDomainService;
    private final OrderRepository orderRepository;

    public OrderHelper(OrderDomainService orderDomainService,
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

    @Transactional
    public OrderUpdatedEvent persistUpdateOrder(Order order) {
        Order oldorder = orderRepository.getById(order);
        OrderUpdatedEvent orderUpdatedEvent = orderDomainService.validateAndUpdateOrder(order);
        Order newOrder = order.builder()
                .build();
        saveOrder(newOrder);
        log.info("initialize order with id: {}", order.getId().getValue());
        return orderUpdatedEvent;
    }

    private void saveOrder(Order order) {
        Order orderResult = orderRepository.save(order);
        log.info("order is saved with id: {}", orderResult.getId().getValue());
    }
}
