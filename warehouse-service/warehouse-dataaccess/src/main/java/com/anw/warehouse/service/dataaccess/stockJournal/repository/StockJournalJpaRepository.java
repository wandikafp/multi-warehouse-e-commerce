package com.anw.warehouse.service.dataaccess.stockJournal.repository;

import com.anw.warehouse.service.dataaccess.stockJournal.entity.StockJournalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StockJournalJpaRepository extends JpaRepository<StockJournalEntity, UUID> {
}
