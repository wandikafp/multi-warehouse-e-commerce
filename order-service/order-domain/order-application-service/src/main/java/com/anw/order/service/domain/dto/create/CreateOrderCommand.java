package com.anw.order.service.domain.dto.create;

import com.anw.domain.valueobject.Address;
import com.anw.order.service.domain.entity.Warehouse;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateOrderCommand {
    private UUID customerId;
    private Address deliveryAddress;
    private Warehouse sourceWarehouse;
    private String paymentMethod;
}
