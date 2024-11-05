package com.anw.warehouse.service.domain.ports.input.service;

import com.anw.warehouse.service.domain.dto.create.CreateStockCommand;
import com.anw.warehouse.service.domain.dto.create.CreateStockResponse;
import com.anw.warehouse.service.domain.dto.update.UpdateStockCommand;
import com.anw.warehouse.service.domain.dto.update.UpdateStockResponse;

public interface StockApplicationService {
    CreateStockResponse creaStock(CreateStockCommand createStockCommand);
}
