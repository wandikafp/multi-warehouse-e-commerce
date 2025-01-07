package com.anw.payment.service.dataaccess.payment.adapter;

import com.anw.domain.dto.PagedRequest;
import com.anw.domain.dto.PagedResponse;
import com.anw.payment.service.dataaccess.payment.entity.PaymentEntity;
import com.anw.payment.service.dataaccess.payment.mapper.PaymentDataAccessMapper;
import com.anw.payment.service.dataaccess.payment.repository.PaymentJpaRepository;
import com.anw.payment.service.domain.entity.Payment;
import com.anw.payment.service.domain.exception.PaymentDomainException;
import com.anw.payment.service.domain.ports.output.repository.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {
    private final PaymentJpaRepository paymentJpaRepository;
    private final PaymentDataAccessMapper paymentDataAccessMapper;

    @Override
    public PagedResponse<Payment> findAll(PagedRequest pagedRequest) {
        Pageable pageable = PageRequest.of(pagedRequest.getPage(), pagedRequest.getSize());
        Page<PaymentEntity> paymentEntities;
        if (StringUtils.hasText(pagedRequest.getQuery())) {
            paymentEntities = paymentJpaRepository.findByNameContainingIgnoreCase(pagedRequest.getQuery(), pageable);
        } else {
            paymentEntities = paymentJpaRepository.findAll(pageable);
        }
        return new PagedResponse<>(paymentEntities.map(paymentDataAccessMapper::paymentEntityToPayment));
    }

    @Override
    public Payment save(Payment payment) {
        return paymentDataAccessMapper.paymentEntityToPayment(
                paymentJpaRepository.save(paymentDataAccessMapper.paymentToPaymentEntity(payment)));
    }

    @Override
    public Payment getById(Payment payment) {
        try {
            return paymentDataAccessMapper.paymentEntityToPayment(
                    paymentJpaRepository.getReferenceById(payment.getId().getValue()));
        } catch (EntityNotFoundException ex) {
            log.error("payment with id {} is not found", payment.getId().getValue());
            throw new PaymentDomainException("payment with id " + payment.getId().getValue() + " is not found");
        }
    }

    @Override
    public Optional<Payment> findById(String paymentId) {
        return paymentJpaRepository.findById(UUID.fromString(paymentId))
                .map(paymentDataAccessMapper::paymentEntityToPayment);
    }

    @Override
    public void deleteById(UUID paymentId) {
        paymentJpaRepository.deleteById(paymentId);
    }
}
