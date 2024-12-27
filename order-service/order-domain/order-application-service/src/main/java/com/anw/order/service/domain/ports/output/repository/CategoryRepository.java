package com.anw.order.service.domain.ports.output.repository;

import com.anw.order.service.domain.entity.Category;
import java.util.Optional;
import java.util.UUID;
import java.util.List;

public interface CategoryRepository {
    Optional<Category> findById(UUID categoryId);
    Optional<Category> findByName(String name);
    Category save(Category category);
    void deleteById(UUID categoryId);
    List<Category> findAll();
}
