package com.anw.payment.service.domain.rest;

import com.anw.domain.dto.PagedRequest;
import com.anw.domain.dto.PagedResponse;
import com.anw.payment.service.domain.dto.PaymentBaseResponse;
import com.anw.payment.service.domain.dto.create.CreatePaymentCommand;
import com.anw.payment.service.domain.dto.create.CreatePaymentResponse;
import com.anw.payment.service.domain.dto.update.UpdatePaymentCommand;
import com.anw.payment.service.domain.dto.update.UpdatePaymentResponse;
import com.anw.payment.service.domain.ports.input.service.PaymentApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/api/payment", produces = "application/json")
public class PaymentController {

    private final PaymentApplicationService paymentApplicationService;

    public PaymentController(PaymentApplicationService paymentApplicationService) {
        this.paymentApplicationService = paymentApplicationService;
    }
    @GetMapping
    public ResponseEntity<PagedResponse<PaymentBaseResponse>> getPayments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "9") int size,
            @RequestParam(defaultValue = "") String query
    ) {
        log.info("Retrieving Payments: ");
        PagedRequest pagedRequest = PagedRequest.builder()
                .page(page)
                .size(size)
                .query(query)
                .build();
        PagedResponse<PaymentBaseResponse> Payments = paymentApplicationService.getPayments(pagedRequest);
        return ResponseEntity.ok(Payments);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentBaseResponse> getPaymentDetail(@PathVariable String paymentId) {
        log.info("Retrieving Payment Detail for id: {}", paymentId);
        PaymentBaseResponse paymentDetail = paymentApplicationService.getPaymentDetail(paymentId);
        return ResponseEntity.ok(paymentDetail);
    }

    @PostMapping
    public ResponseEntity<CreatePaymentResponse> createPayment(@RequestBody CreatePaymentCommand createPaymentCommand) {
        log.info("Creating Payment: ");
        CreatePaymentResponse createPaymentResponse = paymentApplicationService.createPayment(createPaymentCommand);
        log.info("Payment created with id: {}", createPaymentResponse);
        return ResponseEntity.ok(createPaymentResponse);
    }
    @PutMapping
    public ResponseEntity<UpdatePaymentResponse> updatePayment(@RequestBody UpdatePaymentCommand updatePaymentCommand) {
        log.info("Updating Payment: ");
        UpdatePaymentResponse updatePaymentResponse = paymentApplicationService.updatePayment(updatePaymentCommand);
        log.info("Payment updated with id: {}", updatePaymentResponse);
        return ResponseEntity.ok(updatePaymentResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        paymentApplicationService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
