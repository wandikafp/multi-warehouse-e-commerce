package com.anw.payment.service.domain;

import com.anw.domain.event.publisher.DomainEventPublisher;
import com.anw.payment.service.domain.entity.Payment;
import com.anw.payment.service.domain.event.PaymentCreatedEvent;
import com.anw.payment.service.domain.event.PaymentUpdatedEvent;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.anw.domain.DomainConstants.UTC;

@Slf4j
public class PaymentDomainServiceImpl implements PaymentDomainService {
    @Override
    public PaymentCreatedEvent validateAndInitiatePayment(Payment payment, DomainEventPublisher<PaymentCreatedEvent> paymentCreatedEventDomainEventPublisher) {
        payment.initializePayment();
        log.info("payment with id: {} is initiated", payment.getId().getValue().toString());
        return new PaymentCreatedEvent(payment, ZonedDateTime.now(ZoneId.of(UTC)), paymentCreatedEventDomainEventPublisher);
    }
    @Override
    public PaymentUpdatedEvent validateAndUpdatePayment(Payment payment) {
        return new PaymentUpdatedEvent(payment, ZonedDateTime.now(ZoneId.of(UTC)));
    }
}
