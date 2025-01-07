package com.anw.payment.service.domain.dto.update;

import com.anw.payment.service.domain.dto.PaymentBaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdatePaymentCommand extends PaymentBaseCommand {
    private UUID id;
}
