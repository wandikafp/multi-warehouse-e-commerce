package com.anw.warehouse.service.domain.dto.update;

import com.anw.domain.valueobject.Address;
import com.anw.warehouse.service.domain.dto.WarehouseBaseResponse;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@SuperBuilder
@AllArgsConstructor
public class UpdateWarehouseResponse extends WarehouseBaseResponse {

}
