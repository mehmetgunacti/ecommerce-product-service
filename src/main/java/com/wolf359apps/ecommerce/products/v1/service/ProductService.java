package com.wolf359apps.ecommerce.products.v1.service;

import com.wolf359apps.ecommerce.products.v1.entity.Product;
import com.wolf359apps.ecommerce.products.v1.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService {

	private final ProductRepository productRepository;

	public Product save(Product product) {

		return productRepository.save(product);

	}

	public Optional<Product> getById(Long id) {

		return productRepository.findById(id);

	}

	public List<Product> getAll() {

		return productRepository.findAll();

	}

	public Optional<Product> update(Long id, Product product) {

		product.setId(id);
		return Optional.of(productRepository.save(product));

	}

	public void delete(Long id) {

		productRepository.deleteById(id);

	}

}

