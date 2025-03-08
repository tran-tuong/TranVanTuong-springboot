package com.samsung.finalproject.common;

import com.samsung.finalproject.models.entities.Product;
import com.samsung.finalproject.models.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository) {
        return args -> {
            if (productRepository.count() == 0) {
                List<Product> products = List.of(
                        new Product("Laptop Dell XPS", 50000000.0, "https://images.samsung.com/vn/smartphones/galaxy-s25/buy/product_color_navy_MO.png", "High-end laptop for professionals"),
                        new Product("MacBook Pro M2", 50000000.0, "https://images.samsung.com/vn/smartphones/galaxy-s25/buy/product_color_navy_MO.png", "Powerful MacBook with M2 chip"),
                        new Product("Samsung Galaxy S24", 20000000.0, "https://images.samsung.com/vn/smartphones/galaxy-s25/buy/product_color_navy_MO.png", "Flagship Samsung smartphone"),
                        new Product("iPhone 15 Pro", 25000000.0, "https://images.samsung.com/vn/smartphones/galaxy-s25/buy/product_color_navy_MO.png", "Apple's latest premium iPhone"),
                        new Product("Sony WH-1000XM5", 3500000.0, "https://images.samsung.com/vn/smartphones/galaxy-s25/buy/product_color_navy_MO.png", "Noise-canceling headphones"),
                        new Product("Apple Watch Series 9", 5000000.0, "https://images.samsung.com/vn/smartphones/galaxy-s25/buy/product_color_navy_MO.png", "Smartwatch with health tracking"),
                        new Product("iPad Pro M2", 11000000.0, "https://images.samsung.com/vn/smartphones/galaxy-s25/buy/product_color_navy_MO.png", "Tablet for professionals"),
                        new Product("Dell Ultrasharp Monitor", 7000000.0, "https://images.samsung.com/vn/smartphones/galaxy-s25/buy/product_color_navy_MO.png", "4K monitor for creative work"),
                        new Product("Logitech MX Master 3", 1030000.0, "https://images.samsung.com/vn/smartphones/galaxy-s25/buy/product_color_navy_MO.png", "Best ergonomic mouse")
                );

                productRepository.saveAll(products);
                System.out.println("✅ Đã thêm sản phẩm vào database!");
            } else {
                System.out.println("📦 Database đã có sản phẩm, không cần thêm!");
            }
        };
    }
}
