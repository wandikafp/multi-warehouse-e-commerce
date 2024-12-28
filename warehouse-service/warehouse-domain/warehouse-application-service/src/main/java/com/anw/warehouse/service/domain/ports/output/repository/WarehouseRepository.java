package com.anw.warehouse.service.domain.ports.output.repository;

import com.anw.domain.dto.PagedResponse;
import com.anw.warehouse.service.domain.entity.Warehouse;

import java.util.List;
import java.util.UUID;

public interface WarehouseRepository {
    PagedResponse<Warehouse> findAll(int page, int size);
    Warehouse save (Warehouse warehouse);
    Warehouse getById(Warehouse warehouse);
    void deleteById(UUID warehouseId);
}
