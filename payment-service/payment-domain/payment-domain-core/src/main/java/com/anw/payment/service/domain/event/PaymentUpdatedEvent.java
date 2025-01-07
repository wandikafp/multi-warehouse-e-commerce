package com.anw.payment.service.domain.event;

import com.anw.domain.event.publisher.DomainEventPublisher;
import com.anw.payment.service.domain.entity.Payment;

import java.time.ZonedDateTime;

public class PaymentUpdatedEvent extends PaymentEvent {
    public PaymentUpdatedEvent(Payment payment,
                                 ZonedDateTime createdAt) {
        super(payment, createdAt);
    }

    @Override
    public void fire() {
    }
}
