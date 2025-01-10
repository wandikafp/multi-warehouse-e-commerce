package com.anw.warehouse.service.dataaccess.stockJournal.mapper;

import com.anw.domain.valueobject.ProductId;
import com.anw.domain.valueobject.StockId;
import com.anw.domain.valueobject.WarehouseId;
import com.anw.warehouse.service.dataaccess.stockJournal.entity.StockJournalEntity;
import com.anw.warehouse.service.domain.entity.StockJournal;
import org.springframework.stereotype.Component;

@Component
public class StockJournalDataAccessMapper {
    public StockJournalEntity stockJournalToStockJournalEntity(StockJournal stockJournal) {
        return StockJournalEntity.builder()
                .id(stockJournal.getId())
                .stockId(stockJournal.getStockId().getValue())
                .warehouseId(stockJournal.getWarehouseId().getValue())
                .productId(stockJournal.getProductId().getValue())
                .quantityChange(stockJournal.getQuantityChange())
                .reason(stockJournal.getReason())
                .build();
    }

    public StockJournal stockJournalEntityToStockJournal(StockJournalEntity entity) {
        return StockJournal.builder()
                .id(entity.getId())
                .stockId(new StockId(entity.getId()))
                .warehouseId(new WarehouseId(entity.getWarehouseId()))
                .productId(new ProductId(entity.getProductId()))
                .quantityChange(entity.getQuantityChange())
                .reason(entity.getReason())
                .build();
    }
}
