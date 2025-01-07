package com.anw.payment.service.domain.entity;

import com.anw.domain.entity.AggregateRoot;
import com.anw.domain.valueobject.PaymentId;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Payment extends AggregateRoot<PaymentId> {
    private final String name;
    private final String description;
    private final double price;
    private final int stockQuantity;
    private final String imageUrl;


    @Builder
    public Payment(PaymentId paymentId, String name, String description, double price, int stockQuantity, String imageUrl) {
        super.setId(paymentId);
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.imageUrl = imageUrl;
    }

    public void initializePayment() {
        setId(new PaymentId(UUID.randomUUID()));
    }
}
