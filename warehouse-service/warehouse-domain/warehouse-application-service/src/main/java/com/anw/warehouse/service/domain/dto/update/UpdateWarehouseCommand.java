package com.anw.warehouse.service.domain.dto.update;

import com.anw.warehouse.service.domain.dto.WarehouseBaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateWarehouseCommand extends WarehouseBaseCommand {
    private UUID id;
}
