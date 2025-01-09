package com.anw.product.service.domain;

import com.anw.domain.dto.PagedRequest;
import com.anw.domain.valueobject.Money;
import com.anw.product.service.domain.entity.Category;
import com.anw.product.service.domain.entity.Product;
import com.anw.product.service.domain.ports.output.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.math.BigDecimal;
import java.util.UUID;

@EnableJpaRepositories(basePackages = { "com.anw.product.service.dataaccess" })
@EntityScan(basePackages = { "com.anw.product.service.dataaccess" })
@SpringBootApplication(scanBasePackages = "com.anw")
public class ProductServiceApplication implements CommandLineRunner {
    @Autowired
    private ProductRepository productRepository;
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
        if (productCount < 11) {
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
