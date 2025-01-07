package com.anw.payment.service.dataaccess.payment.mapper;

import com.anw.domain.valueobject.PaymentId;
import com.anw.payment.service.dataaccess.payment.entity.PaymentEntity;
import com.anw.payment.service.domain.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentDataAccessMapper {
    public PaymentEntity paymentToPaymentEntity(Payment payment) {
        return PaymentEntity.builder()
                .id(payment.getId().getValue())
                .name(payment.getName())
                .description(payment.getDescription())
                .price(payment.getPrice())
                .stockQuantity(payment.getStockQuantity())
                .imageUrl(payment.getImageUrl())
                .build();
    }

    public Payment paymentEntityToPayment(PaymentEntity entity) {
        return Payment.builder()
                .paymentId(new PaymentId(entity.getId()))
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .stockQuantity(entity.getStockQuantity())
                .imageUrl(entity.getImageUrl())
                .build();
    }
}
