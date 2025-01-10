package com.anw.warehouse.service.dataaccess.stock.repository;

import com.anw.warehouse.service.dataaccess.stock.entity.StockEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StockJpaRepository extends JpaRepository<StockEntity, UUID> {
    Page<StockEntity> findByWarehouseId(UUID warehouseId, Pageable pageable);
    Optional<StockEntity> findByWarehouseIdAndProductId(UUID warehouseId, UUID productId);

}
