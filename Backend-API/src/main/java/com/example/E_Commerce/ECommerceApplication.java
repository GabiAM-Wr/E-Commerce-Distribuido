package com.example.E_Commerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.E_Commerce"})
public class ECommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ECommerceApplication.class, args);
        System.out.println("================================================");
        System.out.println("🚀 E-COMMERCE DISTRIBUIDO - API INICIADA");
        System.out.println("📍 Laptop Principal: 192.168.0.111:8081");
        System.out.println("🔗 Conectando a nodos en:");
        System.out.println("   - MongoDB: Laptop1, Laptop2(192.168.0.36), Laptop3(192.168.0.37)");
        System.out.println("   - Cassandra: Cluster distribuido");
        System.out.println("   - CockroachDB: Cluster distribuido");
        System.out.println("================================================");
    }
}
