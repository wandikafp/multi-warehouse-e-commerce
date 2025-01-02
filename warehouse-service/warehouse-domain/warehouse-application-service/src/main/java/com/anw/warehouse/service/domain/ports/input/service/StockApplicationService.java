package com.anw.warehouse.service.domain.ports.input.service;

import com.anw.domain.dto.PagedResponse;
import com.anw.warehouse.service.domain.dto.StockBaseResponse;
import com.anw.warehouse.service.domain.dto.create.CreateStockCommand;
import com.anw.warehouse.service.domain.dto.create.CreateStockResponse;
import com.anw.warehouse.service.domain.dto.create.CreateStockCommand;
import com.anw.warehouse.service.domain.dto.create.CreateStockResponse;
import com.anw.warehouse.service.domain.dto.update.UpdateStockCommand;
import com.anw.warehouse.service.domain.dto.update.UpdateStockResponse;
import com.anw.warehouse.service.domain.dto.update.UpdateStockCommand;
import com.anw.warehouse.service.domain.dto.update.UpdateStockResponse;
import jakarta.validation.Valid;

import java.util.UUID;

public interface StockApplicationService {
    PagedResponse<StockBaseResponse> getStocksByWarehouseId(UUID warehouseId, int page, int size);
    CreateStockResponse createStock(@Valid CreateStockCommand createStockCommand);
    UpdateStockResponse updateStock(@Valid UpdateStockCommand updateStockCommand);
    void deleteStock(UUID warehouseId);
}
