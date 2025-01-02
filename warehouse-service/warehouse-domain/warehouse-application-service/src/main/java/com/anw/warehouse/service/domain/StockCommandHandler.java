package com.anw.warehouse.service.domain;


import com.anw.domain.dto.PagedResponse;
import com.anw.warehouse.service.domain.dto.StockBaseResponse;
import com.anw.warehouse.service.domain.dto.create.CreateStockCommand;
import com.anw.warehouse.service.domain.dto.create.CreateStockResponse;
import com.anw.warehouse.service.domain.dto.update.UpdateStockCommand;
import com.anw.warehouse.service.domain.dto.update.UpdateStockResponse;
import com.anw.warehouse.service.domain.entity.Stock;
import com.anw.warehouse.service.domain.event.StockCreatedEvent;
import com.anw.warehouse.service.domain.mapper.StockDataMapper;
import com.anw.warehouse.service.domain.ports.output.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class StockCommandHandler {
    private final StockRepository stockRepository;
    private final StockDataMapper stockDataMapper;
    private final StockHelper stockHelper;

    @Transactional
    public PagedResponse<StockBaseResponse> getStocks(UUID warehouseId, int page, int size) {
        PagedResponse<Stock> warehouses = stockRepository.findAllByWarehouseId(warehouseId, page, size);
        return new PagedResponse<>(warehouses.getPage(), warehouses.getSize(), warehouses.getTotalElements(), warehouses.getTotalPages(),
                warehouses.getData()
                        .stream()
                        .map(stockDataMapper::stockToStockBaseResponse)
                        .collect(Collectors.toList()));
    }
    
    public CreateStockResponse createStock(CreateStockCommand createStockCommand) {
        Stock warehouse = stockDataMapper.createStockCommandToStock(createStockCommand);
        StockCreatedEvent stockCreatedEvent = stockHelper.persistCreateStock(warehouse);
        log.info("stock is created with id: {}", stockCreatedEvent.getStock().getId().getValue());
        return stockDataMapper.stockToCreateStockResponse(stockCreatedEvent.getStock());
    }

    public UpdateStockResponse updateStock(UpdateStockCommand updateStockCommand) {
        Stock stock = stockDataMapper.updateStockCommandToStock(updateStockCommand);
        stock = stockHelper.persistUpdateStock(stock);
        return stockDataMapper.stockToUpdateStockResponse(stock);
    }

    @Transactional
    public void deleteStock(UUID stockId) {
        stockHelper.persistRemoveStock(stockId);
    }
}
