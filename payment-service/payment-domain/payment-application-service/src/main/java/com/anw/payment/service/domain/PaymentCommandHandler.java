package com.anw.payment.service.domain;

import com.anw.domain.dto.PagedRequest;
import com.anw.domain.dto.PagedResponse;
import com.anw.payment.service.domain.dto.PaymentBaseResponse;
import com.anw.payment.service.domain.dto.create.CreatePaymentCommand;
import com.anw.payment.service.domain.dto.create.CreatePaymentResponse;
import com.anw.payment.service.domain.dto.update.UpdatePaymentCommand;
import com.anw.payment.service.domain.dto.update.UpdatePaymentResponse;
import com.anw.payment.service.domain.entity.Payment;
import com.anw.payment.service.domain.event.PaymentCreatedEvent;
import com.anw.payment.service.domain.event.PaymentUpdatedEvent;
import com.anw.payment.service.domain.mapper.PaymentDataMapper;
import com.anw.payment.service.domain.ports.output.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentCommandHandler {
    private final PaymentDataMapper paymentDataMapper;
    private final PaymentHelper paymentHelper;
    private final PaymentRepository paymentRepository;

    public PagedResponse<PaymentBaseResponse> getPayments(PagedRequest pagedRequest) {
        PagedResponse<Payment> payments = paymentRepository.findAll(pagedRequest);
        return new PagedResponse<>(payments.getPage(), payments.getSize(), payments.getTotalElements(), payments.getTotalPages(),
                payments.getData()
                        .stream()
                        .map(paymentDataMapper::paymentToPaymentBaseResponse)
                        .collect(Collectors.toList()));
    }

    public PaymentBaseResponse getPaymentDetail(String paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        return paymentDataMapper.paymentToPaymentBaseResponse(payment);
    }

    public CreatePaymentResponse createPayment(CreatePaymentCommand createPaymentCommand) {
        Payment payment = paymentDataMapper.createPaymentCommandToPayment(createPaymentCommand);
        PaymentCreatedEvent paymentCreatedEvent = paymentHelper.persistCreatePayment(payment);
        log.info("payment is created with id: {}", paymentCreatedEvent.getPayment().getId().getValue());
        //Below event just for testing
        return paymentDataMapper.paymentToCreatePaymentResponse(paymentCreatedEvent.getPayment());
    }

    public UpdatePaymentResponse updatePayment(UpdatePaymentCommand updatePaymentCommand) {
        Payment payment = paymentDataMapper.updatePaymentCommandToPayment(updatePaymentCommand);
        PaymentUpdatedEvent paymentUpdatedEvent = paymentHelper.persistUpdatePayment(payment);
        return paymentDataMapper.paymentToUpdatePaymentResponse(paymentUpdatedEvent.getPayment());
    }

    @Transactional
    public void deletePayment(UUID categoryId) {
        paymentRepository.deleteById(categoryId);
    }
}
