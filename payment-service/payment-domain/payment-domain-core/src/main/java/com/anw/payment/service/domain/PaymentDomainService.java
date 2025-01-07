package com.anw.payment.service.domain;

import com.anw.domain.event.publisher.DomainEventPublisher;
import com.anw.payment.service.domain.entity.Payment;
import com.anw.payment.service.domain.event.PaymentCreatedEvent;
import com.anw.payment.service.domain.event.PaymentUpdatedEvent;

public interface PaymentDomainService {
    PaymentCreatedEvent validateAndInitiatePayment(
            Payment payment,
            DomainEventPublisher<PaymentCreatedEvent> paymentCreatedEventDomainEventPublisher);
    PaymentUpdatedEvent validateAndUpdatePayment(Payment payment);
}
