package com.anw.payment.service.domain.ports.input.service;

import com.anw.domain.dto.PagedRequest;
import com.anw.domain.dto.PagedResponse;
import com.anw.payment.service.domain.dto.PaymentBaseResponse;
import com.anw.payment.service.domain.dto.create.CreatePaymentCommand;
import com.anw.payment.service.domain.dto.create.CreatePaymentResponse;
import com.anw.payment.service.domain.dto.update.UpdatePaymentCommand;
import com.anw.payment.service.domain.dto.update.UpdatePaymentResponse;
import jakarta.validation.Valid;

import java.util.UUID;

public interface PaymentApplicationService {
    PagedResponse<PaymentBaseResponse> getPayments(PagedRequest pagedRequest);
    CreatePaymentResponse createPayment(@Valid CreatePaymentCommand createPaymentCommand);
    UpdatePaymentResponse updatePayment(@Valid UpdatePaymentCommand updatePaymentCommand);
    PaymentBaseResponse getPaymentDetail(String paymentId);
    void deletePayment(UUID paymentId);
}
