package com.anw.warehouse.service.domain;

import com.anw.warehouse.service.domain.dto.WarehouseBaseResponse;
import com.anw.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.anw.warehouse.service.domain.dto.create.CreateWarehouseResponse;
import com.anw.warehouse.service.domain.dto.update.UpdateWarehouseCommand;
import com.anw.warehouse.service.domain.dto.update.UpdateWarehouseResponse;
import com.anw.warehouse.service.domain.entity.Warehouse;
import com.anw.warehouse.service.domain.event.WarehouseCreatedEvent;
import com.anw.warehouse.service.domain.event.WarehouseUpdatedEvent;
import com.anw.warehouse.service.domain.mapper.WarehouseDataMapper;
import com.anw.warehouse.service.domain.ports.output.message.publisher.WarehouseCreatedTestRequestMessagePublisher;
import com.anw.warehouse.service.domain.ports.output.repository.WarehouseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class WarehouseCommandHandler {
    private final WarehouseDataMapper warehouseDataMapper;
    private final WarehouseHelper warehouseHelper;
    private final WarehouseRepository warehouseRepository;
    private final WarehouseCreatedTestRequestMessagePublisher warehouseCreatedTestRequestMessagePublisher;

    public WarehouseCommandHandler(WarehouseDataMapper warehouseDataMapper,
                                   WarehouseHelper warehouseHelper,
                                   WarehouseRepository warehouseRepository,
                                   WarehouseCreatedTestRequestMessagePublisher warehouseCreatedTestRequestMessagePublisher) {
        this.warehouseDataMapper = warehouseDataMapper;
        this.warehouseHelper = warehouseHelper;
        this.warehouseRepository = warehouseRepository;
        this.warehouseCreatedTestRequestMessagePublisher = warehouseCreatedTestRequestMessagePublisher;
    }

    public List<WarehouseBaseResponse> getWarehouses(int page, int size) {
        return warehouseRepository.findAll(page, size)
                .stream()
                .map(warehouseDataMapper::warehouseToWarehouseBaseResponse)
                .collect(Collectors.toList());
    }

    public CreateWarehouseResponse createWarehouse(CreateWarehouseCommand createWarehouseCommand) {
        Warehouse warehouse = warehouseDataMapper.createWarehouseCommandToWarehouse(createWarehouseCommand);
        WarehouseCreatedEvent warehouseCreatedEvent = warehouseHelper.persistCreateWarehouse(warehouse);
        log.info("warehouse is created with id: {}", warehouseCreatedEvent.getWarehouse().getId().getValue());
        //Below event just for testing
        warehouseCreatedTestRequestMessagePublisher.publish(warehouseCreatedEvent);
        return warehouseDataMapper.warehouseToCreateWarehouseResponse(warehouseCreatedEvent.getWarehouse());
    }

    public UpdateWarehouseResponse updateWarehouse(UpdateWarehouseCommand updateWarehouseCommand) {
        Warehouse warehouse = warehouseDataMapper.updateWarehouseCommandToWarehouse(updateWarehouseCommand);
        WarehouseUpdatedEvent warehouseUpdatedEvent = warehouseHelper.persistUpdateWarehouse(warehouse);
        return warehouseDataMapper.warehouseToUpdateWarehouseResponse(warehouseUpdatedEvent.getWarehouse());
    }
}
