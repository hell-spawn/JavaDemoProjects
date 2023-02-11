package com.spawn.game.store.product.infrastructure.adapters.input.rest;

import com.spawn.game.store.product.application.ports.input.CreateProductUseCase;
import com.spawn.game.store.product.application.ports.input.QueryProductUseCase;
import com.spawn.game.store.product.domain.models.Product;
import com.spawn.game.store.product.infrastructure.adapters.config.ProductRestMapping;
import com.spawn.game.store.product.infrastructure.adapters.input.rest.data.request.ProductCreateRequest;
import com.spawn.game.store.product.infrastructure.adapters.input.rest.data.response.ProductCreateResponse;
import com.spawn.game.store.product.infrastructure.adapters.input.rest.data.response.ProductSearchResponse;
import com.spawn.game.store.product.infrastructure.adapters.input.rest.mapper.ProductRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(ProductRestMapping.ROOT)
@RequiredArgsConstructor
public class ProductRestAdapter {

    private final CreateProductUseCase createProductUseCase;

    private final QueryProductUseCase queryProductUseCase;

    private final ProductRestMapper productRestMapper;

    @PostMapping(ProductRestMapping.PATH + ProductRestMapping.PATH_CREATE_PRODUCT)
    public ResponseEntity<ProductCreateResponse> createProduct(@RequestBody @Valid ProductCreateRequest productCreateRequest){
        Product product = productRestMapper.toProduct(productCreateRequest);
        product = createProductUseCase.createProduct(product);
        return new ResponseEntity<>(productRestMapper.toProductCreateResponse(product), HttpStatus.OK);
    }

    @GetMapping(ProductRestMapping.PATH + "/{productId}")
    public ResponseEntity<ProductSearchResponse> getProductById(@PathVariable  String productId){
        Product product = queryProductUseCase.searchProductById(productId);
        return new ResponseEntity<>(productRestMapper.toProductQueryResponse(product), HttpStatus.OK);
    }

}
