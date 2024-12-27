package com.anw.order.service.domain.dto.update;

import com.anw.order.service.domain.dto.OrderBaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateOrderCommand extends OrderBaseCommand {
    private UUID id;
}
