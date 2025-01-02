package com.anw.warehouse.service.domain.rest;

import com.anw.domain.dto.PagedResponse;
import com.anw.warehouse.service.domain.dto.WarehouseBaseResponse;
import com.anw.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.anw.warehouse.service.domain.dto.create.CreateWarehouseResponse;
import com.anw.warehouse.service.domain.dto.update.UpdateWarehouseCommand;
import com.anw.warehouse.service.domain.dto.update.UpdateWarehouseResponse;
import com.anw.warehouse.service.domain.ports.input.service.WarehouseApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/api/warehouse", produces = "application/json")
public class WarehouseController {

    private final WarehouseApplicationService warehouseApplicationService;

    public WarehouseController(WarehouseApplicationService warehouseApplicationService) {
        this.warehouseApplicationService = warehouseApplicationService;
    }
    @GetMapping("/generate")
    public ResponseEntity<String> generateUUID() {
        String uuid = UUID.randomUUID().toString();
        log.info("Generated UUID: {}", uuid);
        return ResponseEntity.ok(uuid);
    }
    @GetMapping
    public ResponseEntity<PagedResponse<WarehouseBaseResponse>> getWarehouses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        log.info("Retrieving warehouses: ");
        PagedResponse<WarehouseBaseResponse> warehouses = warehouseApplicationService.getWarehouses(page, size);
        return ResponseEntity.ok(warehouses);
    }
    @PostMapping
    public ResponseEntity<CreateWarehouseResponse> createWarehouse(@RequestBody CreateWarehouseCommand createWarehouseCommand) {
        log.info("Creating warehouse: ");
        CreateWarehouseResponse createWarehouseResponse = warehouseApplicationService.createWarehouse(createWarehouseCommand);
        log.info("Warehouse created with id: {}", createWarehouseResponse);
        return ResponseEntity.ok(createWarehouseResponse);
    }
    @PutMapping
    public ResponseEntity<UpdateWarehouseResponse> updateWarehouse(@RequestBody UpdateWarehouseCommand updateWarehouseCommand) {
        log.info("Updating Warehouse: ");
        UpdateWarehouseResponse updateWarehouseResponse = warehouseApplicationService.updateWarehouse(updateWarehouseCommand);
        log.info("Warehouse updated with id: {}", updateWarehouseResponse);
        return ResponseEntity.ok(updateWarehouseResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable UUID id) {
        warehouseApplicationService.deleteWarehouse(id);
        return ResponseEntity.noContent().build();
    }
}
