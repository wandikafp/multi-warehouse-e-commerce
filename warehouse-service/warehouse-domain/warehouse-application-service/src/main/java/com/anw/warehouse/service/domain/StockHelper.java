package com.anw.warehouse.service.domain;

import com.anw.warehouse.service.domain.entity.Stock;
import com.anw.warehouse.service.domain.entity.StockJournal;
import com.anw.warehouse.service.domain.event.StockCreatedEvent;
import com.anw.warehouse.service.domain.ports.output.repository.StockJournalRepository;
import com.anw.warehouse.service.domain.ports.output.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class StockHelper {
    private final StockRepository stockRepository;
    private final StockDomainService stockDomainService;
    private final StockJournalRepository stockJournalRepository;
    @Transactional
    public StockCreatedEvent persistCreateStock(Stock stock) {
        StockCreatedEvent stockCreatedEvent = stockDomainService.validateAndInitiateStock(stock, null);
        saveStock(stock);
        StockJournal journal = StockJournal.builder()
                .id(UUID.randomUUID())
                .stockId(stock.getId())
                .quantityChange(stock.getQuantity())
                .reason("creation")
                .build();
        log.info("initialize stock with id: {}", stock.getId().getValue());
        saveStockJournal(journal);
        return stockCreatedEvent;
    }

    @Transactional
    public Stock persistUpdateStock(Stock stock) {
        Stock oldStock = stockRepository.getById(stock.getId().getValue());
        StockJournal stockJournal = StockJournal.builder()
                .id(UUID.randomUUID())
                .stockId(oldStock.getId())
                .quantityChange(stock.getQuantity() - oldStock.getQuantity())
                .reason("update")
                .build();
        saveStock(stock);
        saveStockJournal(stockJournal);
        log.info("update stock with id: {}", stock.getId().getValue());
        return stock;
    }

    @Transactional
    public Stock persistRemoveStock(UUID stockId) {
        Stock stock = stockRepository.getById(stockId);
        stock.setQuantity(0);
        StockJournal stockJournal = StockJournal.builder()
                .id(UUID.randomUUID())
                .stockId(stock.getId())
                .quantityChange(stock.getQuantity()*-1)
                .reason("removal")
                .build();
        saveStock(stock);
        saveStockJournal(stockJournal);
        log.info("update stock with id: {}", stock.getId().getValue());
        return stock;
    }

    private void saveStock(Stock stock) {
        Stock stockResult = stockRepository.save(stock);
        log.info("Stock is saved with id: {}", stockResult.getId().getValue());
    }

    private void saveStockJournal(StockJournal stockJournal) {
        StockJournal journal = stockJournalRepository.save(stockJournal);
        log.info("StockJournal is saved with id: {}", journal.getId());
    }
}
