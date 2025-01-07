package com.anw.payment.service.domain.ports.output.repository;

import com.anw.domain.dto.PagedRequest;
import com.anw.domain.dto.PagedResponse;
import com.anw.payment.service.domain.entity.Payment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository {
    PagedResponse<Payment> findAll(PagedRequest pagedRequest);
    Payment save (Payment payment);
    Payment getById(Payment payment);
    Optional<Payment> findById(String paymentId);
    void deleteById(UUID paymentId);
}
