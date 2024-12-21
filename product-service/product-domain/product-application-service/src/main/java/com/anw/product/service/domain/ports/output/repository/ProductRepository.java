package com.anw.product.service.domain.ports.output.repository;

import com.anw.product.service.domain.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll(int page, int size);
    Product save (Product product);
    Product getById(Product product);
    List<Product> search(String query);
    Optional<Product> findById(String productId);
}
