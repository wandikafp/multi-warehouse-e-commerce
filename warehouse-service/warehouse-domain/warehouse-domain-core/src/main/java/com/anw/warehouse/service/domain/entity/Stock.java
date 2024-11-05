package com.anw.warehouse.service.domain.entity;

import com.anw.domain.entity.BaseEntity;
import com.anw.domain.valueobject.ProductId;
import com.anw.domain.valueobject.StockId;
import com.anw.domain.valueobject.WarehouseId;
import com.anw.warehouse.service.domain.exception.StockDomainException;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter

public class Stock extends BaseEntity<StockId> {
    private final WarehouseId warehouseId;
    private final ProductId productId;
    private final Integer amount;

    @Builder
    public Stock(StockId stockId, WarehouseId warehouseId, ProductId productId, Integer amount) {
        super.setId(stockId);
        this.warehouseId = warehouseId;
        this.productId = productId;
        this.amount = amount;
    }

    public void initializeStock() {
        setId(new StockId(UUID.randomUUID()));
    }

    private void validateStockAmount(int size) {
        if (amount == null || amount < size) {
            throw new StockDomainException("Stock amount cannot be empty or less than wanted amount");
        }
    }

}
