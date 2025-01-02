package com.anw.product.service.domain.ports.output.repository;

import com.anw.domain.dto.PagedRequest;
import com.anw.domain.dto.PagedResponse;
import com.anw.product.service.domain.entity.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {
    PagedResponse<Product> findAll(PagedRequest pagedRequest);
    Product save (Product product);
    Product getById(Product product);
    Optional<Product> findById(String productId);
    void deleteById(UUID productId);
}
