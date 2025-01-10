package com.anw.warehouse.service.dataaccess.stockJournal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stock_journal")
public class StockJournalEntity {
    @Id
    private UUID id;
    @Column(nullable = false)
    private UUID stockId;
    @Column(nullable = false)
    private UUID warehouseId;
    @Column(nullable = false)
    private UUID productId;
    @Column(nullable = false)
    private Integer quantityChange;
    private String reason;

    @CreationTimestamp
    @Column(name="created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
}
