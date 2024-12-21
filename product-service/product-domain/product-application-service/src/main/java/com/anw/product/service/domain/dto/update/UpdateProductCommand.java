package com.anw.product.service.domain.dto.update;

import com.anw.product.service.domain.dto.ProductBaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateProductCommand extends ProductBaseCommand {
    private UUID id;
}
