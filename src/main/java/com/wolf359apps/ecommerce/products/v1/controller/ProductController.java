package com.wolf359apps.ecommerce.products.v1.controller;

import com.wolf359apps.ecommerce.products.v1.dto.ProductDTO;
import com.wolf359apps.ecommerce.products.v1.entity.Product;
import com.wolf359apps.ecommerce.products.v1.exception.EntityNotFoundException;
import com.wolf359apps.ecommerce.products.v1.mapper.ProductMapper;
import com.wolf359apps.ecommerce.products.v1.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class ProductController {

	private final ProductService productService;
	private final ProductMapper  productMapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProductDTO createProduct(@RequestBody ProductDTO dto) {

		Product product      = productMapper.toEntity(dto);
		Product savedProduct = productService.save(product);
		return productMapper.toDto(savedProduct);

	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ProductDTO getProductById(@PathVariable Long id) {

		return productService
				.getById(id)
				.map(productMapper::toDto)
				.orElseThrow(() -> new EntityNotFoundException(id));

	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ProductDTO> getAllProducts() {

		return productService
				.getAll()
				.stream()
				.map(productMapper::toDto)
				.toList();

	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ProductDTO putProduct(@PathVariable Long id, @RequestBody ProductDTO dto) {

		return productService
				.update(id, productMapper.toEntity(dto))
				.map(productMapper::toDto)
				.orElseThrow(() -> new EntityNotFoundException(id));

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProduct(@PathVariable Long id) {

		productService.delete(id);

	}

}

