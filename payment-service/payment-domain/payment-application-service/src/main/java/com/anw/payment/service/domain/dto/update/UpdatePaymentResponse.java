package com.anw.payment.service.domain.dto.update;

import com.anw.domain.valueobject.Address;
import com.anw.payment.service.domain.dto.PaymentBaseResponse;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@SuperBuilder
@AllArgsConstructor
public class UpdatePaymentResponse extends PaymentBaseResponse {

}
