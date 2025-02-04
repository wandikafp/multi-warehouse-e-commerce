package com.anw.warehouse.service.dataaccess.warehouse.adapter;

import com.anw.domain.dto.PagedResponse;
import com.anw.warehouse.service.dataaccess.warehouse.mapper.WarehouseDataAccessMapper;
import com.anw.warehouse.service.dataaccess.warehouse.repository.WarehouseJpaRepository;
import com.anw.warehouse.service.domain.entity.Warehouse;
import com.anw.warehouse.service.domain.exception.WarehouseDomainException;
import com.anw.warehouse.service.domain.ports.output.repository.WarehouseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class WarehouseRepositoryImpl implements WarehouseRepository {
    private final WarehouseJpaRepository warehouseJpaRepository;
    private final WarehouseDataAccessMapper warehouseDataAccessMapper;

    public WarehouseRepositoryImpl(WarehouseJpaRepository warehouseJpaRepository,
                                   WarehouseDataAccessMapper warehouseDataAccessMapper) {
        this.warehouseJpaRepository = warehouseJpaRepository;
        this.warehouseDataAccessMapper = warehouseDataAccessMapper;
    }

    @Override
    public PagedResponse<Warehouse> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new PagedResponse<>(warehouseJpaRepository.findAll(pageable)
                .map(warehouseDataAccessMapper::warehouseEntityToWarehouse));
    }

    @Override
    public Warehouse save(Warehouse warehouse) {
        return warehouseDataAccessMapper.warehouseEntityToWarehouse(
                warehouseJpaRepository.save(warehouseDataAccessMapper.warehouseToWarehouseEntity(warehouse)));
    }

    @Override
    public Warehouse getById(Warehouse warehouse) {
        try {
            return warehouseDataAccessMapper.warehouseEntityToWarehouse(
                    warehouseJpaRepository.getReferenceById(warehouse.getId().getValue()));
        } catch (EntityNotFoundException ex) {
            log.error("warehouse with id {} is not found", warehouse.getId().getValue());
            throw new WarehouseDomainException("warehouse with id " + warehouse.getId().getValue() + " is not found");
        }
    }

    @Override
    public void deleteById(UUID warehouseId) {
        warehouseJpaRepository.deleteById(warehouseId);
    }
}
