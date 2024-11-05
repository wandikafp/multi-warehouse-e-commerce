package com.anw.warehouse.service.domain.rest;

import com.anw.warehouse.service.domain.dto.create.CreateStockCommand;
import com.anw.warehouse.service.domain.dto.create.CreateStockResponse;
import com.anw.warehouse.service.domain.dto.update.UpdateStockCommand;
import com.anw.warehouse.service.domain.dto.update.UpdateStockResponse;
import com.anw.warehouse.service.domain.ports.input.service.StockApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/stock", produces = "application/json")
public class StockController {
    private final StockApplicationService stockApplicationService;

    public StockController(StockApplicationService stockApplicationService) {
        this.stockApplicationService = stockApplicationService;
    }
    @PostMapping
    public ResponseEntity<CreateStockResponse> createStock(@RequestBody CreateStockCommand createStockCommand) {
        log.info("Creating stock: ");
        CreateStockResponse createStockResponse = stockApplicationService.creaStock(createStockCommand);
        log.info("Stock created with id: {}", createStockResponse);
        return ResponseEntity.ok(createStockResponse);
    }
//    @PutMapping
//    public ResponseEntity<UpdateStockResponse> updateStock(@RequestBody UpdateStockCommand updateStockCommand) {
//        log.info("Creating stock: ");
//        UpdateStockResponse updateStockResponse = stockApplicationService.updateStock(updateStockCommand);
//        log.info("Stock created with id: {}", updateStockResponse);
//        return ResponseEntity.ok(updateStockResponse);
//    }
}
