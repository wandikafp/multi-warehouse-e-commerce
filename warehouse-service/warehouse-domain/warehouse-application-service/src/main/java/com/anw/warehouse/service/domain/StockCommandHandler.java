package com.anw.warehouse.service.domain;


import com.anw.domain.dto.PagedResponse;
import com.anw.warehouse.service.domain.dto.StockBaseResponse;
import com.anw.warehouse.service.domain.dto.create.CreateStockCommand;
import com.anw.warehouse.service.domain.dto.create.CreateStockResponse;
import com.anw.warehouse.service.domain.dto.update.UpdateStockQuantityCommand;
import com.anw.warehouse.service.domain.dto.update.UpdateStockQuantityResponse;
import com.anw.warehouse.service.domain.entity.Stock;
import com.anw.warehouse.service.domain.entity.StockJournal;
import com.anw.warehouse.service.domain.event.StockCreatedEvent;
import com.anw.warehouse.service.domain.event.StockJournalCreatedEvent;
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
        Stock stock = stockDataMapper.createStockCommandToStock(createStockCommand);
        StockCreatedEvent stockCreatedEvent = stockHelper.persistCreateStock(stock);
        log.info("stock is created with id: {}", stockCreatedEvent.getStock().getId().getValue());
        StockJournal journal = StockJournal.builder()
                .id(UUID.randomUUID())
                .stockId(stock.getId())
                .warehouseId(stock.getWarehouseId())
                .productId(stock.getProductId())
                .quantityChange(stock.getQuantity())
                .reason("creation")
                .build();
        StockJournalCreatedEvent stockJournalCreatedEvent = stockHelper.persistCreateStockJournal(journal);
        stockJournalCreatedEvent.fire();
        return stockDataMapper.stockToCreateStockResponse(stockCreatedEvent.getStock());
    }

    public UpdateStockQuantityResponse updateStockQuantity(UpdateStockQuantityCommand updateStockQuantityCommand) {
        Stock stock = stockDataMapper.updateStockQuantityCommandToStock(updateStockQuantityCommand);
        stock = stockHelper.persistUpdateStockQuantity(stock);
        log.info("stock with id is updated: {}", stock.getId().getValue());
        StockJournal journal = StockJournal.builder()
                .id(UUID.randomUUID())
                .stockId(stock.getId())
                .warehouseId(stock.getWarehouseId())
                .productId(stock.getProductId())
                .quantityChange(stock.getQuantity())
                .reason("quantity modification")
                .build();
        StockJournalCreatedEvent stockJournalCreatedEvent = stockHelper.persistCreateStockJournal(journal);
        stockJournalCreatedEvent.fire();
        return stockDataMapper.stockToUpdateStockResponse(stock);
    }

    @Transactional
    public void deleteStock(UUID stockId) {
        Stock stock = stockHelper.persistRemoveStock(stockId);
        StockJournal journal = StockJournal.builder()
                .id(UUID.randomUUID())
                .stockId(stock.getId())
                .warehouseId(stock.getWarehouseId())
                .productId(stock.getProductId())
                .quantityChange(stock.getQuantity()*-1)
                .reason("removal")
                .build();
        StockJournalCreatedEvent stockJournalCreatedEvent = stockHelper.persistCreateStockJournal(journal);
        stockJournalCreatedEvent.fire();
    }
}
