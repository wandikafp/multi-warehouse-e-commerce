package com.anw.order.service.domain;

import com.anw.domain.event.publisher.DomainEventPublisher;
import com.anw.order.service.domain.entity.Order;
import com.anw.order.service.domain.event.OrderCreatedEvent;
import com.anw.order.service.domain.event.OrderUpdatedEvent;

public interface OrderDomainService {
    OrderCreatedEvent validateAndInitiateOrder(
            Order order,
            DomainEventPublisher<OrderCreatedEvent> orderCreatedEventDomainEventPublisher);
    OrderUpdatedEvent validateAndUpdateOrder(Order order);
}
