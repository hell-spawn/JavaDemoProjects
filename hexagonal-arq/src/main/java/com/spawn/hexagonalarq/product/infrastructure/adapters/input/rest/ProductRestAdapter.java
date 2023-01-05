package com.spawn.hexagonalarq.product.infrastructure.adapters.input.rest;

import com.spawn.hexagonalarq.product.application.ports.input.CreateProductUseCase;
import com.spawn.hexagonalarq.product.application.ports.input.GetProductUseCase;
import com.spawn.hexagonalarq.product.domain.models.Product;
import com.spawn.hexagonalarq.product.infrastructure.adapters.config.ProductMapping;
import com.spawn.hexagonalarq.product.infrastructure.adapters.input.rest.data.request.ProductCreateRequest;
import com.spawn.hexagonalarq.product.infrastructure.adapters.input.rest.data.response.ProductCreateResponse;
import com.spawn.hexagonalarq.product.infrastructure.adapters.input.rest.data.response.ProductQueryResponse;
import com.spawn.hexagonalarq.product.infrastructure.adapters.input.rest.mapper.ProductRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(ProductMapping.ROOT)
@RequiredArgsConstructor
public class ProductRestAdapter {

    private final CreateProductUseCase createProductUseCase;
    private final GetProductUseCase getProductUseCase;
    private final ProductRestMapper productRestMapper;

    @PostMapping(ProductMapping.PATH)
    public ResponseEntity<ProductCreateResponse> createProduct(@RequestBody @Valid ProductCreateRequest productCreateRequest){
        Product product = productRestMapper.toProduct(productCreateRequest);
        product = createProductUseCase.createProduct(product);
        return new ResponseEntity<>(productRestMapper.toProductCreateResponse(product), HttpStatus.OK);
    }

    @GetMapping(ProductMapping.PATH)
    public ResponseEntity<List<ProductQueryResponse>> findAll(){
        List<Product> products = getProductUseCase.findAll();
        return new ResponseEntity<>( productRestMapper.map(products), HttpStatus.OK);
    }

    @GetMapping(ProductMapping.PATH + "/{id}")
    public ResponseEntity<ProductQueryResponse> findProductById(@PathVariable Long id){
        Product product = getProductUseCase.findProductById(id);
        return new ResponseEntity<>( productRestMapper.toProductQueryResponse(product), HttpStatus.OK);
    }
}
