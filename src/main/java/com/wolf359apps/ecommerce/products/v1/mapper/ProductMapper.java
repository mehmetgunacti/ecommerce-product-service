package com.wolf359apps.ecommerce.products.v1.mapper;

import com.wolf359apps.ecommerce.products.v1.dto.ProductDTO;
import com.wolf359apps.ecommerce.products.v1.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

	Product toEntity(ProductDTO dto);

	ProductDTO toDto(Product entity);

	@Mapping(target = "id", ignore = true)
	void copyValues(Product source, @MappingTarget Product target);

}
