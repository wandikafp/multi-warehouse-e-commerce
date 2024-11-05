package com.anw.warehouse.service.domain.dto.update;

import com.anw.warehouse.service.domain.dto.WarehouseAddress;
import com.anw.warehouse.service.domain.dto.WarehouseBaseCommand;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateWarehouseCommand extends WarehouseBaseCommand {
    private UUID id;
}
