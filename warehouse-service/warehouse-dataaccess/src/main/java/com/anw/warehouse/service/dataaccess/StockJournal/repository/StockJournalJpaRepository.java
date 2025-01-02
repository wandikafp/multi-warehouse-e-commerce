package com.anw.warehouse.service.dataaccess.StockJournal.repository;

import com.anw.warehouse.service.dataaccess.StockJournal.entity.StockJournalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StockJournalJpaRepository extends JpaRepository<StockJournalEntity, UUID> {
}
