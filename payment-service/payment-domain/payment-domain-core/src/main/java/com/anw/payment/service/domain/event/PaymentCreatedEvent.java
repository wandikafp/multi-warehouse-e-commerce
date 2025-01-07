package com.anw.payment.service.domain.event;

import com.anw.domain.event.publisher.DomainEventPublisher;
import com.anw.payment.service.domain.entity.Payment;

import java.time.ZonedDateTime;

public class PaymentCreatedEvent extends PaymentEvent {
    private final DomainEventPublisher<PaymentCreatedEvent> paymentCreatedEventDomainEventPublisher;

    public PaymentCreatedEvent(Payment payment,
                                 ZonedDateTime createdAt,
                                 DomainEventPublisher<PaymentCreatedEvent> paymentCreatedEventDomainEventPublisher) {
        super(payment, createdAt);
        this.paymentCreatedEventDomainEventPublisher = paymentCreatedEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        paymentCreatedEventDomainEventPublisher.publish(this);
    }
}
