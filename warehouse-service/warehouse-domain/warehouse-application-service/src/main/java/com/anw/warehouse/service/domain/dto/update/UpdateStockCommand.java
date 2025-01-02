package com.anw.warehouse.service.domain.dto.update;

import com.anw.warehouse.service.domain.dto.StockBaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateStockCommand extends StockBaseCommand {
    private UUID id;
}
