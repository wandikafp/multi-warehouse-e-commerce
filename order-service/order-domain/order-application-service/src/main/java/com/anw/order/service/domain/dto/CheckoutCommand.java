package com.anw.order.service.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CheckoutCommand {
    private UUID customerId;
    private Address deliveryAddress;
}
