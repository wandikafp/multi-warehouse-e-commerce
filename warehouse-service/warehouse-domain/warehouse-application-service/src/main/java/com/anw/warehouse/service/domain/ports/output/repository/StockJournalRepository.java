package com.anw.warehouse.service.domain.ports.output.repository;

import com.anw.warehouse.service.domain.entity.StockJournal;

public interface StockJournalRepository {
    StockJournal save (StockJournal stockJournal);
}
