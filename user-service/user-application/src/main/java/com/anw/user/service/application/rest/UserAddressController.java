package com.anw.user.service.application.rest;

import com.anw.user.service.domain.dto.user.UserAddressCommand;
import com.anw.user.service.domain.dto.user.UserAddressResponse;
import com.anw.user.service.domain.ports.input.service.UserAddressApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/api/user-address")
@RequiredArgsConstructor
public class UserAddressController {
    private final UserAddressApplicationService userAddressApplicationService;

    @PostMapping
    public ResponseEntity<UserAddressResponse> create(@Valid @RequestBody UserAddressCommand request) {
        UserAddressResponse userAddress = userAddressApplicationService.create(request);
        return ResponseEntity.ok(userAddress);
    }

    @PutMapping
    public ResponseEntity<UserAddressResponse> update(@Valid @RequestBody UserAddressCommand request) {
        UserAddressResponse userAddress = userAddressApplicationService.update(request);
        return ResponseEntity.ok(userAddress);
    }

    @GetMapping
    public ResponseEntity<List<UserAddressResponse>> getUserAddresses(@RequestParam UUID userId) {
        List<UserAddressResponse> userAddressResponses = userAddressApplicationService.getAll(userId);
        return ResponseEntity.ok(userAddressResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAddressResponse> getUserAddress(@PathVariable UUID id) {
        UserAddressResponse userAddressResponse = userAddressApplicationService.get(id);
        return ResponseEntity.ok(userAddressResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        userAddressApplicationService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
