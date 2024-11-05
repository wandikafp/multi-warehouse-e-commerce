package com.anw.warehouse.service.domain.ports.output.repository;

import com.anw.warehouse.service.domain.entity.Warehouse;

import java.util.List;

public interface WarehouseRepository {
    List<Warehouse> findAll(int page, int size);
    Warehouse save (Warehouse warehouse);
    Warehouse getById(Warehouse warehouse);
}
