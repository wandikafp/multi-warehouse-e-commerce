package com.anw.warehouse.service.domain.ports.output.repository;

import com.anw.domain.dto.PagedResponse;
import com.anw.warehouse.service.domain.entity.Stock;

import java.util.UUID;

public interface StockRepository {
    PagedResponse<Stock> findAll(int page, int size);
    PagedResponse<Stock> findAllByWarehouseId(UUID warehouseId, int page, int size);
    Stock save (Stock stock);
    Stock getById(UUID id);
    void deleteById(UUID stockId);
}
