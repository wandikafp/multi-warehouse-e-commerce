package com.anw.warehouse.service.domain;


import com.anw.warehouse.service.domain.dto.create.CreateStockCommand;
import com.anw.warehouse.service.domain.dto.create.CreateStockResponse;
import com.anw.warehouse.service.domain.entity.Stock;
import com.anw.warehouse.service.domain.mapper.StockDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StockCommandHandler {
    private final StockDataMapper stockDataMapper;
//    private final StockHelper stockHelper;
//    private final StockRepository stockRepository;

    public StockCommandHandler(StockDataMapper stockDataMapper) {
        this.stockDataMapper = stockDataMapper;
//        this.stockHelper = stockHelper;
//        this.stockRepository = stockRepository;
    }

    public CreateStockResponse createStock(CreateStockCommand createStockCommand) {
        Stock Stock = stockDataMapper.createStockCommandToStock(createStockCommand);
//        return StockDataMapper.StockToCreateStockResponse(StockCreatedEvent.getStock());
        return null;
    }
}
