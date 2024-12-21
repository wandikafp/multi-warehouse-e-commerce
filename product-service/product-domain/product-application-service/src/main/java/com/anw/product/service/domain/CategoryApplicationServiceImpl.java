package com.anw.product.service.domain;

import com.anw.product.service.domain.entity.Category;
import com.anw.product.service.domain.ports.input.service.CategoryApplicationService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CategoryApplicationServiceImpl implements CategoryApplicationService {
    private final CategoryCommandHandler categoryCommandHandler;

    public CategoryApplicationServiceImpl(CategoryCommandHandler categoryCommandHandler) {
        this.categoryCommandHandler = categoryCommandHandler;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryCommandHandler.getAllCategories();
    }

    @Override
    public Optional<Category> getCategoryById(UUID categoryId) {
        return categoryCommandHandler.getCategoryById(categoryId);
    }

    @Override
    public Category createCategory(Category category) {
        return categoryCommandHandler.createCategory(category);
    }

    @Override
    public Category updateCategory(UUID categoryId, Category category) {
        return categoryCommandHandler.updateCategory(categoryId, category);
    }

    @Override
    public void deleteCategory(UUID categoryId) {
        categoryCommandHandler.deleteCategory(categoryId);
    }
}
