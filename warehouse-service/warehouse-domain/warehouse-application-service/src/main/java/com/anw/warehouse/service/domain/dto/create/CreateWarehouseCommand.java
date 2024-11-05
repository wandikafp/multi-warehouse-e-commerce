package com.anw.warehouse.service.domain.dto.create;

import com.anw.warehouse.service.domain.dto.WarehouseAddress;
import com.anw.warehouse.service.domain.dto.WarehouseBaseCommand;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateWarehouseCommand extends WarehouseBaseCommand {

}
