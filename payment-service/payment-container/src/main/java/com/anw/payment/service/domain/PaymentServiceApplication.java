package com.anw.payment.service.domain;

import com.anw.payment.service.domain.ports.output.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = { "com.anw.payment.service.dataaccess" })
@EntityScan(basePackages = { "com.anw.payment.service.dataaccess" })
@SpringBootApplication(scanBasePackages = "com.anw")
public class PaymentServiceApplication {
    @Autowired
    private PaymentRepository paymentRepository;
    public static void main(String[] args) {
        SpringApplication.run(PaymentServiceApplication.class, args);
    }
}
