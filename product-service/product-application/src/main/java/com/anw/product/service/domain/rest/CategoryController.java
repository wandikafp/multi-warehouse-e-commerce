package com.anw.product.service.domain.rest;

import com.anw.product.service.domain.CategoryApplicationServiceImpl;
import com.anw.product.service.domain.entity.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryApplicationServiceImpl categoryApplicationServiceImpl;

    public CategoryController(CategoryApplicationServiceImpl categoryApplicationServiceImpl) {
        this.categoryApplicationServiceImpl = categoryApplicationServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryApplicationServiceImpl.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable UUID id) {
        Optional<Category> category = categoryApplicationServiceImpl.getCategoryById(id);
        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return ResponseEntity.ok(categoryApplicationServiceImpl.createCategory(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable UUID id, @RequestBody Category category) {
        return ResponseEntity.ok(categoryApplicationServiceImpl.updateCategory(id, category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        categoryApplicationServiceImpl.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
