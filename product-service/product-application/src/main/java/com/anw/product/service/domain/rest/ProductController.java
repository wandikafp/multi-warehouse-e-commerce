package com.anw.product.service.domain.rest;

import com.anw.product.service.domain.dto.ProductBaseResponse;
import com.anw.product.service.domain.dto.create.CreateProductCommand;
import com.anw.product.service.domain.dto.create.CreateProductResponse;
import com.anw.product.service.domain.dto.update.UpdateProductCommand;
import com.anw.product.service.domain.dto.update.UpdateProductResponse;
import com.anw.product.service.domain.ports.input.service.ProductApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/Product", produces = "application/json")
public class ProductController {

    private final ProductApplicationService productApplicationService;

    public ProductController(ProductApplicationService productApplicationService) {
        this.productApplicationService = productApplicationService;
    }
    @GetMapping
    public ResponseEntity<List<ProductBaseResponse>> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        log.info("Retrieving Products: ");
        List<ProductBaseResponse> Products = productApplicationService.getProducts(page, size);
        return ResponseEntity.ok(Products);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductBaseResponse>> searchProducts(@RequestParam String query) {
        log.info("Searching Products with query: {}", query);
        List<ProductBaseResponse> products = productApplicationService.searchProducts(query);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductBaseResponse> getProductDetail(@PathVariable String productId) {
        log.info("Retrieving Product Detail for id: {}", productId);
        ProductBaseResponse productDetail = productApplicationService.getProductDetail(productId);
        return ResponseEntity.ok(productDetail);
    }

    @PostMapping
    public ResponseEntity<CreateProductResponse> createProduct(@RequestBody CreateProductCommand createProductCommand) {
        log.info("Creating Product: ");
        CreateProductResponse createProductResponse = productApplicationService.createProduct(createProductCommand);
        log.info("Product created with id: {}", createProductResponse);
        return ResponseEntity.ok(createProductResponse);
    }
    @PutMapping
    public ResponseEntity<UpdateProductResponse> updateProduct(@RequestBody UpdateProductCommand updateProductCommand) {
        log.info("Updating Product: ");
        UpdateProductResponse updateProductResponse = productApplicationService.updateProduct(updateProductCommand);
        log.info("Product updated with id: {}", updateProductResponse);
        return ResponseEntity.ok(updateProductResponse);
    }
}
