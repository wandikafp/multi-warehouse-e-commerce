package com.anw.warehouse.service.domain;

import com.anw.warehouse.service.domain.dto.create.CreateStockCommand;
import com.anw.warehouse.service.domain.dto.create.CreateStockResponse;
import com.anw.warehouse.service.domain.ports.input.service.StockApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StockApplicationServiceImpl implements StockApplicationService {
    private final StockCommandHandler stockCommandHandler;
    StockApplicationServiceImpl(StockCommandHandler stockCommandHandler) {
        this.stockCommandHandler = stockCommandHandler;
    }
    @Override
    public CreateStockResponse creaStock(CreateStockCommand createStockCommand) {
        return stockCommandHandler.createStock(createStockCommand);
    }
}
