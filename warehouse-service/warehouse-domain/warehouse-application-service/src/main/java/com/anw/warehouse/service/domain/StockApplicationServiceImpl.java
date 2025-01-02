package com.anw.warehouse.service.domain;

import com.anw.domain.dto.PagedResponse;
import com.anw.warehouse.service.domain.dto.StockBaseResponse;
import com.anw.warehouse.service.domain.dto.create.CreateStockCommand;
import com.anw.warehouse.service.domain.dto.create.CreateStockResponse;
import com.anw.warehouse.service.domain.dto.update.UpdateStockCommand;
import com.anw.warehouse.service.domain.dto.update.UpdateStockResponse;
import com.anw.warehouse.service.domain.ports.input.service.StockApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockApplicationServiceImpl implements StockApplicationService {
    private final StockCommandHandler stockCommandHandler;

    @Override
    public PagedResponse<StockBaseResponse> getStocksByWarehouseId(UUID warehouseId, int page, int size) {
        return stockCommandHandler.getStocks(warehouseId, page, size);
    }

    @Override
    public CreateStockResponse createStock(CreateStockCommand createStockCommand) {
        return stockCommandHandler.createStock(createStockCommand);
    }

    @Override
    public UpdateStockResponse updateStock(UpdateStockCommand updateStockCommand) {
        return stockCommandHandler.updateStock(updateStockCommand);
    }

    @Override
    public void deleteStock(UUID stockId) {
        stockCommandHandler.deleteStock(stockId);
    }
}
