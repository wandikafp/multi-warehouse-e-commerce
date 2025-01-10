package com.anw.warehouse.service.domain.mapper;

import com.anw.domain.valueobject.ProductId;
import com.anw.domain.valueobject.StockId;
import com.anw.domain.valueobject.WarehouseId;
import com.anw.warehouse.service.domain.dto.StockBaseResponse;
import com.anw.warehouse.service.domain.dto.create.CreateStockCommand;
import com.anw.warehouse.service.domain.dto.create.CreateStockResponse;
import com.anw.warehouse.service.domain.dto.update.UpdateStockQuantityCommand;
import com.anw.warehouse.service.domain.dto.update.UpdateStockQuantityResponse;
import com.anw.warehouse.service.domain.entity.Stock;
import org.springframework.stereotype.Component;

@Component
public class StockDataMapper {
    public StockBaseResponse stockToStockBaseResponse(Stock stock) {
        return StockBaseResponse.builder()
                .id(stock.getId().getValue())
                .warehouseId(stock.getWarehouseId().getValue())
                .productId(stock.getProductId().getValue())
                .quantity(stock.getQuantity())
                .build();
    }

    public Stock createStockCommandToStock(CreateStockCommand command) {
        return Stock.builder()
                .warehouseId(new WarehouseId(command.getWarehouseId()))
                .productId(new ProductId(command.getProductId()))
                .quantity(command.getQuantity())
                .build();
    }

    public CreateStockResponse stockToCreateStockResponse(Stock stock) {
        return CreateStockResponse.builder()
                .id(stock.getId().getValue())
                .warehouseId(stock.getWarehouseId().getValue())
                .productId(stock.getProductId().getValue())
                .quantity(stock.getQuantity())
                .build();
    }

    public Stock updateStockQuantityCommandToStock(UpdateStockQuantityCommand command) {
        return Stock.builder()
                .stockId(new StockId(command.getId()))
                .warehouseId(new WarehouseId(command.getWarehouseId()))
                .productId(new ProductId(command.getProductId()))
                .quantity(command.getQuantity())
                .build();
    }

    public UpdateStockQuantityResponse stockToUpdateStockResponse(Stock stock) {
        return UpdateStockQuantityResponse.builder()
                .id(stock.getId().getValue())
                .warehouseId(stock.getWarehouseId().getValue())
                .productId(stock.getProductId().getValue())
                .quantity(stock.getQuantity())
                .build();
    }
}
