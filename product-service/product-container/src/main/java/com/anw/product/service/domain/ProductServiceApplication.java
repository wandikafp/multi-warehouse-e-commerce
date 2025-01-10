package com.anw.product.service.domain;

import com.anw.domain.dto.PagedRequest;
import com.anw.domain.valueobject.Money;
import com.anw.product.service.domain.entity.Category;
import com.anw.product.service.domain.entity.Product;
import com.anw.product.service.domain.ports.output.repository.CategoryRepository;
import com.anw.product.service.domain.ports.output.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@EnableJpaRepositories(basePackages = { "com.anw.product.service.dataaccess" })
@EntityScan(basePackages = { "com.anw.product.service.dataaccess" })
@SpringBootApplication(scanBasePackages = "com.anw")
public class ProductServiceApplication implements CommandLineRunner {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("User Service Application is running");
        int productCount = productRepository.findAll(PagedRequest.builder()
                        .page(0)
                        .size(20)
                        .build())
                .getData()
                .size();
        List<Category> categories = List.of(
                Category.builder()
                        .categoryId(UUID.fromString("75ed3119-8385-4bad-aa8f-055eb9678eb5"))
                        .description("Find your next great read.")
                        .icon("\uD83D\uDCDA")
                        .name("Books")
                        .build(),
                Category.builder()
                        .categoryId(UUID.fromString("79f6a6b5-833d-4ed9-9e09-452e031ab70c"))
                        .description("Upgrade your living space with our products.")
                        .icon("\uD83C\uDFE0")
                        .name("Home Appliances")
                        .build(),
                Category.builder()
                        .categoryId(UUID.fromString("7d8f53ed-7a33-4089-bcc2-667932739f4d"))
                        .description("Fun and engaging toys for all ages.")
                        .icon("\uD83E\uDDF8")
                        .name("Toys")
                        .build(),
                Category.builder()
                        .categoryId(UUID.fromString("a954d9f4-775f-4b70-be49-4099cb1ee325"))
                        .description("Explore trending styles and accessories.")
                        .icon("\uD83D\uDC57")
                        .name("Fashion")
                        .build(),
                Category.builder()
                        .categoryId(UUID.fromString("b9844ed2-ab57-4f40-916e-99d894a98c95"))
                        .description("Discover the latest gadgets and devices.")
                        .icon("\uD83D\uDCF1")
                        .name("Electronics")
                        .build()
        );
        if (productCount < 11) {
            for(Category category : categories) {
                categoryRepository.save(category);
            }
            for (int i=0; i<11; i++) {
                Product product = Product.builder()
                        .name("Product " + i)
                        .description("Product Description " + i)
                        .price(new Money(new BigDecimal(100.0 * i)))
                        .imageUrl("/images/logo.png")
                        .stockQuantity(0)
                        .category(new Category(UUID.fromString("b9844ed2-ab57-4f40-916e-99d894a98c95")
                                ,"", "", ""))
                        .build();
                product.initializeProduct();
                productRepository.save(product);
            }
        }
    }
}
