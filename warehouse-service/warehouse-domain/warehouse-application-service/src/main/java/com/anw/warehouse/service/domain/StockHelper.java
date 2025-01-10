package com.anw.warehouse.service.domain;

import com.anw.warehouse.service.domain.entity.Stock;
import com.anw.warehouse.service.domain.entity.StockJournal;
import com.anw.warehouse.service.domain.event.StockCreatedEvent;
import com.anw.warehouse.service.domain.event.StockJournalCreatedEvent;
import com.anw.warehouse.service.domain.ports.output.message.publisher.StockJournalCreatedMessagePublisher;
import com.anw.warehouse.service.domain.ports.output.repository.StockJournalRepository;
import com.anw.warehouse.service.domain.ports.output.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

import static com.anw.domain.DomainConstants.UTC;

@Slf4j
@Component
@RequiredArgsConstructor
public class StockHelper {
    private final StockRepository stockRepository;
    private final StockDomainService stockDomainService;
    private final StockJournalRepository stockJournalRepository;
    private final StockJournalCreatedMessagePublisher stockCreatedEventDomainEventPublisher;

    @Transactional
    public StockCreatedEvent persistCreateStock(Stock stock) {
        Stock existingStock = stockRepository.findByWarehouseIdAndProductId(stock.getWarehouseId().getValue(), stock.getProductId().getValue());
        StockCreatedEvent stockCreatedEvent;
        if (existingStock == null) {
            stockCreatedEvent = stockDomainService.validateAndInitiateStock(stock, null);
            saveStock(stock);
        } else {
            existingStock.setQuantity(stock.getQuantity());
            stockCreatedEvent = new StockCreatedEvent(stock, ZonedDateTime.now(ZoneId.of(UTC)), null);
            saveStock(existingStock);
        }
        return stockCreatedEvent;
    }

    @Transactional
    public Stock persistUpdateStockQuantity(Stock stock) {
        Stock oldStockObject = stockRepository.getById(stock.getId().getValue());
        oldStockObject.setQuantity(stock.getQuantity());
        saveStock(stock);
        log.info("update stock quantity with id: {}", stock.getId().getValue());
        return stock;
    }

    @Transactional
    public Stock persistRemoveStock(UUID stockId) {
        Stock stock = stockRepository.getById(stockId);
        stock.setQuantity(0);
        saveStock(stock);
        log.info("delete stock quantity with id: {}", stock.getId().getValue());
        return stock;
    }

    private void saveStock(Stock stock) {
        Stock stockResult = stockRepository.save(stock);
        log.info("Stock is saved with id: {}", stockResult.getId().getValue());
    }

    @Transactional
    public StockJournalCreatedEvent persistCreateStockJournal(StockJournal stockJournal) {
        saveStockJournal(stockJournal);
        return new StockJournalCreatedEvent(stockJournal, ZonedDateTime.now(ZoneId.of(UTC)), stockCreatedEventDomainEventPublisher);
    }

    private void saveStockJournal(StockJournal stockJournal) {
        StockJournal journal = stockJournalRepository.save(stockJournal);
        log.info("StockJournal is saved with id: {}", journal.getId());
    }
}
