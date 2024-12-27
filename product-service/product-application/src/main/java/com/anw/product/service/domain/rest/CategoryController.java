package com.anw.product.service.domain.rest;

import com.anw.product.service.domain.entity.Category;
import com.anw.product.service.domain.ports.input.service.CategoryApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {
    private final CategoryApplicationService categoryApplicationService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryApplicationService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable UUID id) {
        Optional<Category> category = categoryApplicationService.getCategoryById(id);
        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return ResponseEntity.ok(categoryApplicationService.createCategory(category));
    }

    @PutMapping
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
        log.info("Updating category with id: {}", category.getId());
        return ResponseEntity.ok(categoryApplicationService.updateCategory(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        categoryApplicationService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
