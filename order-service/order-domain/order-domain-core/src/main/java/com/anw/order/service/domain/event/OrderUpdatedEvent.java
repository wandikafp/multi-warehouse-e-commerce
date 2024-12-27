package com.anw.order.service.domain.event;

import com.anw.domain.event.publisher.DomainEventPublisher;
import com.anw.order.service.domain.entity.Order;

import java.time.ZonedDateTime;

public class OrderUpdatedEvent extends OrderEvent {
    public OrderUpdatedEvent(Order order,
                                 ZonedDateTime createdAt) {
        super(order, createdAt);
    }

    @Override
    public void fire() {
    }
}
