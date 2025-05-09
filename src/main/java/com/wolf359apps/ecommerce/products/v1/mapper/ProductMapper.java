package com.wolf359apps.ecommerce.products.v1.mapper;

import com.wolf359apps.ecommerce.products.v1.dto.ProductDTO;
import com.wolf359apps.ecommerce.products.v1.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

	Product toEntity(ProductDTO dto);

	ProductDTO toDto(Product entity);

}
