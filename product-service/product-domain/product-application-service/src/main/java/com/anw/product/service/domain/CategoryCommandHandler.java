package com.anw.product.service.domain;

import com.anw.product.service.domain.entity.Category;
import com.anw.product.service.domain.ports.output.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CategoryCommandHandler {
    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(UUID categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Transactional
    public Category createCategory(Category category) {
        if (categoryRepository.findByName(category.getName()).isPresent()) {
            throw new IllegalArgumentException("Category with the same name already exists.");
        }
        category.initializeCategory();
        return categoryRepository.save(category);
    }

    @Transactional
    public Category updateCategory(Category category) {
        if (categoryRepository.findById(category.getId()).isEmpty()) {
            throw new IllegalArgumentException("Category does not exists.");
        }
        return categoryRepository.save(category);
    }

    @Transactional
    public void deleteCategory(UUID categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
