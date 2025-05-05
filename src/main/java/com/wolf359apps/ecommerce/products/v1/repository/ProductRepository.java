package com.wolf359apps.ecommerce.products.v1.repository;

import com.wolf359apps.ecommerce.products.v1.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}