package com.anw.warehouse.service.dataaccess.stockJournal.adapter;

import com.anw.warehouse.service.dataaccess.stockJournal.mapper.StockJournalDataAccessMapper;
import com.anw.warehouse.service.dataaccess.stockJournal.repository.StockJournalJpaRepository;
import com.anw.warehouse.service.domain.entity.StockJournal;
import com.anw.warehouse.service.domain.ports.output.repository.StockJournalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class StockJournalRepositoryImpl implements StockJournalRepository {
    private final StockJournalJpaRepository stockJournalJpaRepository;
    private final StockJournalDataAccessMapper stockJournalDataAccessMapper;

    @Override
    public StockJournal save(StockJournal stockJournal) {
        return stockJournalDataAccessMapper.stockJournalEntityToStockJournal(
                stockJournalJpaRepository.save(stockJournalDataAccessMapper.stockJournalToStockJournalEntity(stockJournal)));
    }
}
