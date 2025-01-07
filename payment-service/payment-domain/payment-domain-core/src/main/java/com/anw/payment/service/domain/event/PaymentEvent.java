package com.anw.payment.service.domain.event;

import com.anw.domain.event.DomainEvent;
import com.anw.payment.service.domain.entity.Payment;

import java.time.ZonedDateTime;

public abstract class PaymentEvent implements DomainEvent<Payment> {
    private final Payment payment;
    private final ZonedDateTime createdAt;
    public PaymentEvent(Payment payment, ZonedDateTime createdAt) {
        this.payment = payment;
        this.createdAt = createdAt;
    }

    public Payment getPayment() {
        return payment;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
