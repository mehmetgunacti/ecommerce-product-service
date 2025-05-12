package com.wolf359apps.ecommerce.products.v1.exception;

public class EntityNotFoundException extends RuntimeException {

	public EntityNotFoundException(Long id) {
		super("Id [%s] not found".formatted(id));
	}

}