package com.wolf359apps.ecommerce.products.repository;

import com.wolf359apps.ecommerce.products.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}