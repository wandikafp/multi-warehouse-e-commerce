package com.anw.warehouse.service.domain.ports.input.service;

import com.anw.domain.dto.PagedResponse;
import com.anw.warehouse.service.domain.dto.WarehouseBaseResponse;
import com.anw.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.anw.warehouse.service.domain.dto.create.CreateWarehouseResponse;
import com.anw.warehouse.service.domain.dto.update.UpdateWarehouseCommand;
import com.anw.warehouse.service.domain.dto.update.UpdateWarehouseResponse;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

public interface WarehouseApplicationService {
    PagedResponse<WarehouseBaseResponse> getWarehouses(int page, int size);
    CreateWarehouseResponse createWarehouse(@Valid CreateWarehouseCommand createWarehouseCommand);
    UpdateWarehouseResponse updateWarehouse(@Valid UpdateWarehouseCommand updateWarehouseCommand);
    void deleteWarehouse(UUID warehouseId);
}
