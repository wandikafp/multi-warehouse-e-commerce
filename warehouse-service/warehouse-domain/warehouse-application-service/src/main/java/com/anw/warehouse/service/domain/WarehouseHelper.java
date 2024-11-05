package com.anw.warehouse.service.domain;

import com.anw.domain.valueobject.UserId;
import com.anw.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.anw.warehouse.service.domain.entity.Warehouse;
import com.anw.warehouse.service.domain.event.WarehouseCreatedEvent;
import com.anw.warehouse.service.domain.event.WarehouseUpdatedEvent;
import com.anw.warehouse.service.domain.ports.output.repository.WarehouseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.anw.domain.DomainConstants.UTC;

@Slf4j
@Component
public class WarehouseHelper {
    private final WarehouseDomainService warehouseDomainService;
    private final WarehouseRepository warehouseRepository;

    public WarehouseHelper(WarehouseDomainService warehouseDomainService,
                           WarehouseRepository warehouseRepository) {
        this.warehouseDomainService = warehouseDomainService;
        this.warehouseRepository = warehouseRepository;
    }

    @Transactional
    public WarehouseCreatedEvent persistCreateWarehouse(Warehouse warehouse) {
        WarehouseCreatedEvent warehouseCreatedEvent = warehouseDomainService.validateAndInitiateWarehouse(warehouse, null);
        saveWarehouse(warehouse);
        log.info("initialize warehouse with id: {}", warehouse.getId().getValue());
        return warehouseCreatedEvent;
    }

    @Transactional
    public WarehouseUpdatedEvent persistUpdateWarehouse(Warehouse warehouse) {
        Warehouse oldWarehouse = warehouseRepository.getById(warehouse);
        WarehouseUpdatedEvent warehouseUpdatedEvent = warehouseDomainService.validateAndUpdateWarehouse(warehouse);
        Warehouse newWarehouse = Warehouse.builder()
                .adminId(new UserId(warehouse.getAdminId().getValue()))
                .locationAddress(warehouse.getLocationAddress())
                .createdAt(oldWarehouse.getCreatedAt())
                .updatedAt(ZonedDateTime.now(ZoneId.of(UTC)))
                .build();
        saveWarehouse(newWarehouse);
        log.info("initialize warehouse with id: {}", warehouse.getId().getValue());
        return warehouseUpdatedEvent;
    }

    private void saveWarehouse(Warehouse warehouse) {
        Warehouse warehouseResult = warehouseRepository.save(warehouse);
        log.info("Warehouse is saved with id: {}", warehouseResult.getId().getValue());
    }
}
