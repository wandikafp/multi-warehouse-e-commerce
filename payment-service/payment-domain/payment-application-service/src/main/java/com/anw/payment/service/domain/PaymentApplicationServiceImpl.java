package com.anw.payment.service.domain;

import com.anw.domain.dto.PagedRequest;
import com.anw.domain.dto.PagedResponse;
import com.anw.payment.service.domain.dto.PaymentBaseResponse;
import com.anw.payment.service.domain.dto.create.CreatePaymentCommand;
import com.anw.payment.service.domain.dto.create.CreatePaymentResponse;
import com.anw.payment.service.domain.dto.update.UpdatePaymentCommand;
import com.anw.payment.service.domain.dto.update.UpdatePaymentResponse;
import com.anw.payment.service.domain.ports.input.service.PaymentApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class PaymentApplicationServiceImpl implements PaymentApplicationService {
    private final PaymentCommandHandler paymentCommandHandler;
    PaymentApplicationServiceImpl(PaymentCommandHandler paymentCommandHandler) {
        this.paymentCommandHandler = paymentCommandHandler;
    }

    @Override
    public PagedResponse<PaymentBaseResponse> getPayments(PagedRequest pagedRequest) {
        return paymentCommandHandler.getPayments(pagedRequest);
    }

    @Override
    public CreatePaymentResponse createPayment(CreatePaymentCommand createPaymentCommand) {
        return paymentCommandHandler.createPayment(createPaymentCommand);
    }

    @Override
    public UpdatePaymentResponse updatePayment(UpdatePaymentCommand updatePaymentCommand) {
        return paymentCommandHandler.updatePayment(updatePaymentCommand);
    }

    @Override
    public PaymentBaseResponse getPaymentDetail(String paymentId) {
        return paymentCommandHandler.getPaymentDetail(paymentId);
    }

    @Override
    public void deletePayment(UUID paymentId) {
        paymentCommandHandler.deletePayment(paymentId);
    }
}
