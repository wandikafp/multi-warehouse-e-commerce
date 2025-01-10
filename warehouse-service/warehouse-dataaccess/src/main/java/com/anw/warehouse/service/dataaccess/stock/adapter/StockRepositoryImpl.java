package com.anw.warehouse.service.dataaccess.stock.adapter;

import com.anw.domain.dto.PagedResponse;
import com.anw.warehouse.service.dataaccess.stock.mapper.StockDataAccessMapper;
import com.anw.warehouse.service.dataaccess.stock.repository.StockJpaRepository;
import com.anw.warehouse.service.domain.entity.Stock;
import com.anw.warehouse.service.domain.exception.WarehouseDomainException;
import com.anw.warehouse.service.domain.ports.output.repository.StockRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class StockRepositoryImpl implements StockRepository {
    private final StockJpaRepository stockJpaRepository;
    private final StockDataAccessMapper stockDataAccessMapper;

    public StockRepositoryImpl(StockJpaRepository stockJpaRepository,
                               StockDataAccessMapper stockDataAccessMapper) {
        this.stockJpaRepository = stockJpaRepository;
        this.stockDataAccessMapper = stockDataAccessMapper;
    }

    @Override
    public PagedResponse<Stock> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new PagedResponse<>(stockJpaRepository.findAll(pageable)
                .map(stockDataAccessMapper::stockEntityToStock));
    }

    @Override
    public PagedResponse<Stock> findAllByWarehouseId(UUID warehouseId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new PagedResponse<>(stockJpaRepository.findByWarehouseId(warehouseId, pageable)
                .map(stockDataAccessMapper::stockEntityToStock));
    }

    @Override
    public Stock save(Stock stock) {
        return stockDataAccessMapper.stockEntityToStock(
                stockJpaRepository.save(stockDataAccessMapper.stockToStockEntity(stock)));
    }

    @Override
    public Stock findByWarehouseIdAndProductId(UUID warehouseId, UUID productId) {
        return stockJpaRepository.findByWarehouseIdAndProductId(warehouseId, productId)
                .map(stockDataAccessMapper::stockEntityToStock)
                .orElse(null);
    }

    @Override
    public Stock getById(UUID id) {
        try {
            return stockDataAccessMapper.stockEntityToStock(
                    stockJpaRepository.getReferenceById(id));
        } catch (EntityNotFoundException ex) {
            log.error("stock with id {} is not found", id);
            throw new WarehouseDomainException("stock with id " + id + " is not found");
        }
    }
}
