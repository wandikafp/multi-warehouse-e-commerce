package com.anw.warehouse.service.domain.rest;

import com.anw.domain.dto.PagedResponse;
import com.anw.warehouse.service.domain.dto.StockBaseResponse;
import com.anw.warehouse.service.domain.dto.create.CreateStockCommand;
import com.anw.warehouse.service.domain.dto.create.CreateStockResponse;
import com.anw.warehouse.service.domain.dto.update.UpdateStockCommand;
import com.anw.warehouse.service.domain.dto.update.UpdateStockResponse;
import com.anw.warehouse.service.domain.ports.input.service.StockApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/api/stock", produces = "application/json")
public class StockController {
    private final StockApplicationService stockApplicationService;

    public StockController(StockApplicationService stockApplicationService) {
        this.stockApplicationService = stockApplicationService;
    }

    @GetMapping
    public ResponseEntity<PagedResponse<StockBaseResponse>> getWarehouses(
            @RequestParam UUID warehouseId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        log.info("Retrieving warehouses: ");
        PagedResponse<StockBaseResponse> stocks = stockApplicationService.getStocksByWarehouseId(warehouseId, page, size);
        return ResponseEntity.ok(stocks);
    }

    @PostMapping
    public ResponseEntity<CreateStockResponse> createStock(@RequestBody CreateStockCommand createStockCommand) {
        log.info("Creating stock: ");
        CreateStockResponse createStockResponse = stockApplicationService.createStock(createStockCommand);
        log.info("Stock created with id: {}", createStockResponse);
        return ResponseEntity.ok(createStockResponse);
    }
    @PutMapping
    public ResponseEntity<UpdateStockResponse> updateStock(@RequestBody UpdateStockCommand updateStockCommand) {
        log.info("Updating stock: ");
        UpdateStockResponse updateStockResponse = stockApplicationService.updateStock(updateStockCommand);
        log.info("Stock updated with id: {}", updateStockResponse);
        return ResponseEntity.ok(updateStockResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable UUID id) {
        stockApplicationService.deleteStock(id);
        return ResponseEntity.noContent().build();
    }
}
