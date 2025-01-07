package com.anw.payment.service.domain;

import com.anw.payment.service.domain.entity.Payment;
import com.anw.payment.service.domain.event.PaymentCreatedEvent;
import com.anw.payment.service.domain.event.PaymentUpdatedEvent;
import com.anw.payment.service.domain.ports.output.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class PaymentHelper {
    private final PaymentDomainService paymentDomainService;
    private final PaymentRepository paymentRepository;

    public PaymentHelper(PaymentDomainService paymentDomainService,
                           PaymentRepository paymentRepository) {
        this.paymentDomainService = paymentDomainService;
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public PaymentCreatedEvent persistCreatePayment(Payment payment) {
        PaymentCreatedEvent paymentCreatedEvent = paymentDomainService.validateAndInitiatePayment(payment, null);
        savePayment(payment);
        log.info("initialize payment with id: {}", payment.getId().getValue());
        return paymentCreatedEvent;
    }

    @Transactional
    public PaymentUpdatedEvent persistUpdatePayment(Payment payment) {
        PaymentUpdatedEvent paymentUpdatedEvent = paymentDomainService.validateAndUpdatePayment(payment);
        savePayment(payment);
        log.info("initialize payment with id: {}", payment.getId().getValue());
        return paymentUpdatedEvent;
    }

    private void savePayment(Payment payment) {
        Payment paymentResult = paymentRepository.save(payment);
        log.info("payment is saved with id: {}", paymentResult.getId().getValue());
    }
}
