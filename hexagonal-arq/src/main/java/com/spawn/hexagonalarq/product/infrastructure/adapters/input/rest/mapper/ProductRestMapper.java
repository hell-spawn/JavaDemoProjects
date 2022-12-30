package com.spawn.hexagonalarq.product.infrastructure.adapters.input.rest.mapper;

import com.spawn.hexagonalarq.product.domain.models.Product;
import com.spawn.hexagonalarq.product.infrastructure.adapters.input.rest.data.request.ProductCreateRequest;
import com.spawn.hexagonalarq.product.infrastructure.adapters.input.rest.data.response.ProductCreateResponse;
import com.spawn.hexagonalarq.product.infrastructure.adapters.input.rest.data.response.ProductQueryResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductRestMapper {

    Product toProduct(ProductCreateRequest productCreateRequest);

    ProductCreateResponse toProductCreateResponse(Product product);

    ProductQueryResponse toProductQueryResponse(Product product);
}
