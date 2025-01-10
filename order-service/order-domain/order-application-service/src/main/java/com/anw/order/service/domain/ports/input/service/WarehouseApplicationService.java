package com.anw.order.service.domain.ports.input.service;

import com.anw.domain.valueobject.Address;
import com.anw.order.service.domain.entity.Warehouse;

import java.util.Optional;

public interface WarehouseApplicationService {
    Optional<Warehouse> getNearestWarehouse(Address deliveryAddress);
}
