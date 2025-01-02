package com.anw.warehouse.service.dataaccess.stock.mapper;

import com.anw.domain.valueobject.*;
import com.anw.warehouse.service.dataaccess.stock.entity.StockEntity;
import com.anw.warehouse.service.domain.entity.Stock;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StockDataAccessMapper {
    public StockEntity stockToStockEntity(Stock stock) {
        return StockEntity.builder()
                .id(stock.getId().getValue())
                .warehouseId(stock.getWarehouseId().getValue())
                .productId(stock.getProductId().getValue())
                .quantity(stock.getQuantity())
                .build();
    }

    public Stock stockEntityToStock(StockEntity entity) {
        return Stock.builder()
                .stockId(new StockId(entity.getId()))
                .warehouseId(new WarehouseId(entity.getWarehouseId()))
                .productId(new ProductId(entity.getProductId()))
                .quantity(entity.getQuantity())
                .build();
    }
}
