package com.anw.order.service.domain;

import com.anw.domain.event.publisher.DomainEventPublisher;
import com.anw.order.service.domain.entity.Order;
import com.anw.order.service.domain.event.OrderCreatedEvent;
import com.anw.order.service.domain.event.OrderUpdatedEvent;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.anw.domain.DomainConstants.UTC;

@Slf4j
public class OrderDomainServiceImpl implements OrderDomainService {
    @Override
    public OrderCreatedEvent validateAndInitiateOrder(Order order, DomainEventPublisher<OrderCreatedEvent> orderCreatedEventDomainEventPublisher) {
        order.initializeOrder();
        log.info("order with id: {} is initiated", order.getId().getValue().toString());
        return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of(UTC)), orderCreatedEventDomainEventPublisher);
    }
    @Override
    public OrderUpdatedEvent validateAndUpdateOrder(Order order) {
        return new OrderUpdatedEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
    }
}
