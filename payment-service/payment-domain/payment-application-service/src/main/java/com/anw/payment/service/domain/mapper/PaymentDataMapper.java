package com.anw.payment.service.domain.mapper;

import com.anw.domain.valueobject.PaymentId;
import com.anw.payment.service.domain.dto.PaymentBaseResponse;
import com.anw.payment.service.domain.dto.create.CreatePaymentCommand;
import com.anw.payment.service.domain.dto.create.CreatePaymentResponse;
import com.anw.payment.service.domain.dto.update.UpdatePaymentCommand;
import com.anw.payment.service.domain.dto.update.UpdatePaymentResponse;
import com.anw.payment.service.domain.entity.Payment;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentDataMapper {
    public PaymentBaseResponse paymentToPaymentBaseResponse(Payment payment) {
        return PaymentBaseResponse.builder()
                .id(payment.getId().getValue())
                .name(payment.getName())
                .description(payment.getDescription())
                .price(payment.getPrice())
                .stockQuantity(payment.getStockQuantity())
                .imageUrl(payment.getImageUrl())
                .build();
    }

    public Payment createPaymentCommandToPayment(CreatePaymentCommand createPaymentCommand) {
        return Payment.builder()
                .paymentId(new PaymentId(UUID.randomUUID()))
                .name(createPaymentCommand.getName())
                .description(createPaymentCommand.getDescription())
                .price(createPaymentCommand.getPrice())
                .stockQuantity(createPaymentCommand.getStockQuantity())
                .imageUrl(createPaymentCommand.getImageUrl())
                .build();
    }

    public CreatePaymentResponse paymentToCreatePaymentResponse(Payment payment) {
        return CreatePaymentResponse.builder()
                .id(payment.getId().getValue())
                .name(payment.getName())
                .description(payment.getDescription())
                .price(payment.getPrice())
                .stockQuantity(payment.getStockQuantity())
                .imageUrl(payment.getImageUrl())
                .build();
    }

    public Payment updatePaymentCommandToPayment(UpdatePaymentCommand updatePaymentCommand) {
        return Payment.builder()
                .paymentId(new PaymentId(updatePaymentCommand.getId()))
                .name(updatePaymentCommand.getName())
                .description(updatePaymentCommand.getDescription())
                .price(updatePaymentCommand.getPrice())
                .stockQuantity(updatePaymentCommand.getStockQuantity())
                .imageUrl(updatePaymentCommand.getImageUrl())
                .build();
    }

    public UpdatePaymentResponse paymentToUpdatePaymentResponse(Payment payment) {
        return UpdatePaymentResponse.builder()
                .id(payment.getId().getValue())
                .name(payment.getName())
                .description(payment.getDescription())
                .price(payment.getPrice())
                .stockQuantity(payment.getStockQuantity())
                .imageUrl(payment.getImageUrl())
                .build();
    }
}
