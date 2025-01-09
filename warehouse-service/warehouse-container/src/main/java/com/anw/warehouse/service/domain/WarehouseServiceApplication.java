package com.anw.warehouse.service.domain;

import com.anw.warehouse.service.domain.entity.Warehouse;
import com.anw.warehouse.service.domain.ports.output.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories(basePackages = { "com.anw.warehouse.service.dataaccess" })
@EntityScan(basePackages = { "com.anw.warehouse.service.dataaccess" })
@SpringBootApplication(scanBasePackages = "com.anw")
public class WarehouseServiceApplication implements CommandLineRunner {
    @Autowired
    private WarehouseRepository warehouseRepository;
    public static void main(String[] args) {
        SpringApplication.run(WarehouseServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("User Service Application is running");
        int productCount = warehouseRepository.findAll(0, 20)
                .getData()
                .size();
        if (productCount < 5) {
            List<Double> lats = List.of(-6.2001, -6.2154, -6.1898, -6.2342, -6.2088);
            List<Double> lons = List.of(106.8457, 106.8322, 106.8593, 106.8235, 106.8451);
            for (int i=0; i<5; i++) {
                Warehouse warehouse = Warehouse.builder()
                        .name("Warehouse " + i)
                        .street("Jl. Jakarta no. " + i)
                        .city("Jakarta")
                        .province("DKI Jakarta")
                        .postalCode("12345")
                        .latitude(lats.get(i))
                        .longitude(lons.get(i))
                        .build();
                warehouse.initializeWarehouse();
                warehouseRepository.save(warehouse);
            }
        }
    }
}
