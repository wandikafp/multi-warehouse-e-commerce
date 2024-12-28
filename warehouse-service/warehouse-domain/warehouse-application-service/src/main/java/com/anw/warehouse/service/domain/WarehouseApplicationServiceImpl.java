package com.anw.warehouse.service.domain;

import com.anw.domain.dto.PagedResponse;
import com.anw.warehouse.service.domain.dto.WarehouseBaseResponse;
import com.anw.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.anw.warehouse.service.domain.dto.create.CreateWarehouseResponse;
import com.anw.warehouse.service.domain.dto.update.UpdateWarehouseCommand;
import com.anw.warehouse.service.domain.dto.update.UpdateWarehouseResponse;
import com.anw.warehouse.service.domain.ports.input.service.WarehouseApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class WarehouseApplicationServiceImpl implements WarehouseApplicationService {
    private final WarehouseCommandHandler warehouseCommandHandler;
    WarehouseApplicationServiceImpl(WarehouseCommandHandler warehouseCommandHandler) {
        this.warehouseCommandHandler = warehouseCommandHandler;
    }

    @Override
    public PagedResponse<WarehouseBaseResponse> getWarehouses(int page, int size) {
        return warehouseCommandHandler.getWarehouses(page, size);
    }

    @Override
    public CreateWarehouseResponse createWarehouse(CreateWarehouseCommand createWarehouseCommand) {
        return warehouseCommandHandler.createWarehouse(createWarehouseCommand);
    }

    @Override
    public UpdateWarehouseResponse updateWarehouse(UpdateWarehouseCommand updateWarehouseCommand) {
        return warehouseCommandHandler.updateWarehouse(updateWarehouseCommand);
    }

    @Override
    public void deleteWarehouse(UUID warehouseId) {
        warehouseCommandHandler.deleteWarehouse(warehouseId);
    }
}
