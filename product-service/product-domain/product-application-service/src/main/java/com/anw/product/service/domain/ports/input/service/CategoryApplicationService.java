package com.anw.product.service.domain.ports.input.service;

import com.anw.product.service.domain.entity.Category;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryApplicationService {
    List<Category> getAllCategories();
    Optional<Category> getCategoryById(UUID categoryId);
    Category createCategory(Category category);
    Category updateCategory(UUID categoryId, Category category);
    void deleteCategory(UUID categoryId);
}
